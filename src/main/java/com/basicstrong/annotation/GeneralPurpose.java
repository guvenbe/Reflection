package com.basicstrong.annotation;

import java.util.ArrayList;

class Parent{
    public void m1(){
        System.out.println(" m1 Parent implementation");
    }
    @Deprecated(since = "2")
    public void m2(){
        System.out.println(" m2 Parent implementation");
    }
}

public class GeneralPurpose extends Parent {
    @Override
    public void m1(){
        System.out.println(" m1 child implementation");
    }
    public static void main(String[] args) {

        @SuppressWarnings("unused")
        int a = 10;

        @SuppressWarnings({"rawtypes", "unused"})
        //@SuppressWarnings("all")
        ArrayList aList= new ArrayList();

        GeneralPurpose obj = new GeneralPurpose();
        obj.m1();

        MyFunctionalInterface impl = () -> System.out.println("Method invoked");
        impl.method();
    }

    @FunctionalInterface
    interface MyFunctionalInterface{
        public void method();
    }
}
