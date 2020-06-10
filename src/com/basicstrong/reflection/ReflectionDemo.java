package com.basicstrong.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Mylass{
    private Mylass() {
        System.out.println("MyClass object created");
    }
}

class Mylass2{
    Mylass2() {
        System.out.println("MyClass object created");
    }
}

public class ReflectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        //Mylass obj = new Mylass(); Not possible since private constructor

        Class<?> clss = Class.forName("com.basicstrong.reflection.Mylass");
        Constructor<?> con = clss.getDeclaredConstructor();
        con.setAccessible(true);
//        Object newInstance = con.newInstance();
        Mylass newInstance = (Mylass)con.newInstance();
        // 3 way  to instantiate a class
        //1 forName()

        Class<?> clss1 = Class.forName("java.lang.String");
        Class<?> clss2 = Class.forName("java.lang.String");

        System.out.println(clss1==clss2); //true

        //2 Classname.class


        Class<?> clss3 = int.class;
        Class<?> clss4 = int.class;
        System.out.println(clss3==clss4); ///true

        // 3 obj.classname
        Mylass2 m = new Mylass2();
        Class<? extends Mylass2> class1 = m.getClass();

        //super
        Class<?> superclass = class1.getSuperclass();

        //Interfaces
        Class<?>[] interfaces = class1.getInterfaces();

        //getName()
        System.out.println(class1.getName());


    }
}
