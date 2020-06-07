package com.balazsholczer.udemy;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection1 {
    /**
     * What is reflection?
     * <p>
     * Reflection is a language's ability to inspect and dynamically call
     * classes, methods, attributes
     * <p>
     * getClass() -> we can get the class ... we do not know at compile time
     * <p>
     * Why to use reflection?
     * <p>
     * Reflection is important since it lets you write programs that do not have
     * to "know" everything at compile time, making them more dynamic, since
     * they can be tied together at runtime !!!
     * <p>
     * - lots of modern frameworks uses reflection extensively for this reason -
     * one very common use case in Java is the usage with annotations
     * <p>
     * JUnit -> will use reflection to look through your classes for methods
     * tagged with the @Test annotation -> will then call them when running the
     * unit test !!!
     */

    public static void main(String[] args) {
        Class<Person> personClass2 = null;

        Class<Person> personClass = Person.class;
        try {
            personClass2 = (Class<Person>) Class.forName("com.balazsholczer.udemy.Person");
            System.out.println(personClass2.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(personClass.getName());
        System.out.println(personClass2.getPackage());

        Field[] fields = personClass2.getFields();

        for (Field field : fields) {
            System.out.println(field.getName() + " " + field.getType());
        }


        System.out.println("*****************declared fields ************************");
        Field[] decFields = personClass2.getDeclaredFields();
        for (Field field : decFields) {
            field.setAccessible(true);
            System.out.println(field.getName() + " " + field.getType());
        }

        Method[] methods = personClass2.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + " return type:" + method.getReturnType());
        }

        System.out.println("*****************declare methods ************************");

        Method[] methodsPriv = personClass2.getDeclaredMethods();
        for (Method privMehod : methodsPriv) {
            privMehod.setAccessible(true);
            System.out.println(privMehod);
        }

        System.out.println("*****************super class ************************");
        System.out.println(personClass.getSuperclass().getName());
        System.out.println("*****************implemented Interfaces ************************");
        Class[] interfaces = personClass.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c.getName());
        }
        System.out.println();

        System.out.println("*****************Annotation ************************");
        Method[] methods1 =personClass.getMethods();
        for (Method m: methods1){
            if (m.isAnnotationPresent(MyAnnotation.class)){
                System.out.println(m.getName());
            }
        }

    }
}

class Employee {

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation {
    public String name();
}

class Person extends Employee implements Comparable<Person>, Serializable {
    public String name;
    public int age;
    private String privateName;
    private int privateAge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @MyAnnotation(name = "myAnnotation")
    public String returnName() {
        return this.name + " is the name!";
    }

    private String returnPrivName() {
        return this.name + " is the name!";
    }


    @Override
    public int compareTo(Person o) {
        return 0;
    }
}