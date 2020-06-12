package com.basicstrong.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class FieldInfo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Entity e = new Entity(10,"id");
        Class<? extends Entity> clss = e.getClass();

        Field[] fields = clss.getFields();

        List<Field> fields1 = Arrays.asList(fields);
        fields1.forEach(f->System.out.println(f.getName()));

        Field[] declaredFields = clss.getDeclaredFields();
        for (Field field: declaredFields){
            System.out.println(field.getName());
        }
        //non declared: all the public elements in that class and in it's super class
        //declared: all the elements present in that class only
        Field field = clss.getField("type");
        Field field2 = clss.getDeclaredField("val");
        field2.setAccessible(true);
        field2.set(e,20);

        field.set(e, "rollNo.");

        System.out.println(e.getType() + " " + e.getVal());

    }
}
