package com.basicstrong.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInfo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Entity e = new Entity(10, "id");
        Class<? extends Entity> clazz = e.getClass();

        Method[] methods = clazz.getMethods();
        for(Method method: methods){
            System.out.println(method.getName());
        }

        System.out.println("----------------------------------------------------------");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method method: declaredMethods){
            System.out.println(method.getName());
        }
        Method method = clazz.getMethod("setVal", int.class);
        method.invoke(e,15);
        Method method1 = clazz.getMethod("setType", String.class);
        method1.invoke(e,"id2");
        Method getVal = clazz.getMethod("getVal", null);
        Method myMethod = clazz.getDeclaredMethod("myMethod", int.class);
        myMethod.setAccessible(true);


        System.out.println("----------------------------------------------------------");
        System.out.println(getVal.invoke(e) + " " + e.getType() );
        myMethod.invoke(e,70);


    }
}
