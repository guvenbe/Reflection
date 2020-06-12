package com.basicstrong.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReadingAnnotation {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("com.basicstrong.annotation.Utility");
        Constructor<?> constructor = clazz.getConstructor();
        Utility u = (Utility) constructor.newInstance();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MostUsed.class)) {
                method.setAccessible(true);
                method.invoke(u,"Scala");

                MostUsed annotation = method.getAnnotation(MostUsed.class);
                String value = annotation.value();//Get the default value of annotation
                method.invoke(u, value);

            }

        }

    }
}
