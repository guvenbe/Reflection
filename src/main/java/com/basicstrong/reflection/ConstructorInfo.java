package com.basicstrong.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorInfo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("com.basicstrong.reflection.Entity");
        Constructor<?>[] constructors = clazz.getConstructors();

        for (Constructor constructor: constructors){
            System.out.println(constructor.getName());
        }

        Constructor<?>[] clazzDeclaredConstructor = clazz.getDeclaredConstructors();
        System.out.println(("------------------------------------------------------------------"));
        for(Constructor constructor: clazzDeclaredConstructor){
            System.out.println(constructor);
        }
        Constructor<?> publicConstructor = clazz.getConstructor(int.class, String.class);
        Entity e = (Entity) publicConstructor.newInstance(20, "studentId");
        System.out.println(e.getVal() + " " + e.getType());

        Constructor<?> privateConstructor = clazz.getDeclaredConstructor();
        privateConstructor.setAccessible(true);
        Entity e1 = (Entity) privateConstructor.newInstance();
        System.out.println(e1.getVal() + " " + e1.getType());


    }
}
