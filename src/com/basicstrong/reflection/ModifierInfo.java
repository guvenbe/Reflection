package com.basicstrong.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierInfo {
    public static void main(String[] args) throws NoSuchMethodException {
        Entity e = new Entity(10, "id");

        Class<? extends Entity> clazz = e.getClass();
        int modifiersInt = clazz.getModifiers();

        int i = modifiersInt & Modifier.PUBLIC;
        System.out.println(i); //0 means not public

        Method getValMethod = clazz.getMethod("getVal");
        int methodModifiers = getValMethod.getModifiers();
        int i1 =  methodModifiers & Modifier.PRIVATE;
        System.out.println(i1);

        //or
        boolean isPublicMethod= Modifier.isPublic(methodModifiers);
        System.out.println(isPublicMethod);
        System.out.println(Modifier.toString(methodModifiers));

        boolean isPublicClass = Modifier.isPublic(modifiersInt);
        System.out.println(isPublicClass);
        System.out.println(Modifier.toString(methodModifiers));



    }

}
