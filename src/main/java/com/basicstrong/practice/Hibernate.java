package com.basicstrong.practice;

import com.basicstrong.annotation.Column;
import com.basicstrong.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hibernate<T> {
    private Connection con;
    private AtomicLong id = new AtomicLong(0L);

    public static <T> Hibernate<T> getConnection() throws SQLException {
        return new Hibernate<T>();

    }

    private Hibernate() throws SQLException {
        this.con = DriverManager.getConnection("jdbc:h2:/home/vagrant/IdeaProjects/Reflection/database/practice1", "sa", "");
    }

    public void write(T t) throws IllegalAccessException, SQLException {
        Class<? extends Object> clss = t.getClass();
        Field[] declaredFields = clss.getDeclaredFields();

        Field pkey = null;
        ArrayList<Field> columns = new ArrayList<>();
        StringJoiner joiner = new StringJoiner(",");

        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                pkey = field;
//                System.out.println("This Primary Key is : " + field.getName() + " VALUE : " + field.get(t) + " and the columns are :");
            } else if (field.isAnnotationPresent(Column.class)) {
                joiner.add(field.getName());
                columns.add(field);
//                System.out.println(field.getName() + " VALUE: " + field.get(t));

            }

        }
        int number = columns.size() + 1;
        System.out.println(joiner.toString());
        System.out.println("number=" +number);

        String qMarks = IntStream.range(0, number)
                .mapToObj(e -> "?")
                .collect(Collectors.joining(","));

        String sql = "insert into " + clss.getSimpleName() + "( " + pkey.getName() +", " + joiner.toString() + ") " + "values (" + qMarks + ")";
        PreparedStatement stmt = con.prepareStatement(sql);

        if (pkey.getType() == long.class) {
            stmt.setLong(1, id.incrementAndGet());
        }
        int index = 2;
        for (Field field : columns) {
            field.setAccessible(true);
            if (field.getType() == int.class) {
                stmt.setInt(index++, (int) field.get(t));
            } else if (field.getType()==String.class){
                stmt.setString(index++, (String) field.get(t));
            }
        }
        stmt.executeUpdate();
    }

    public T read(Class<T> clss, long l) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Field[] declaredFields = clss.getDeclaredFields();
        Field pkey= null;
        for (Field field : declaredFields) {
            if(field.isAnnotationPresent(PrimaryKey.class)){
                pkey= field;
                break;
            }
        }
        String sql = "select * from " + clss.getSimpleName() +" where " + pkey.getName() +" = " + l ;
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();

        T t = clss.getConstructor().newInstance();
        long transationId = rs.getInt(pkey.getName());

        pkey.setAccessible(true);
        pkey.set(t, transationId);
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                if (field.getType()==int.class){
                    field.set(t, rs.getInt(field.getName()));
                } else if (field.getType() == String.class) {
                    field.set(t, rs.getString(field.getName()));
                }
            }
        }
        return t;
    }
}
