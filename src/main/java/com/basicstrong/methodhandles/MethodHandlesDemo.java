package com.basicstrong.methodhandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

public class MethodHandlesDemo {
    public static void main(String[] args) throws Throwable {
        Lookup lookup = MethodHandles.lookup();
        Class<?> clss = lookup.findClass(Student.class.getName());

        Student s = new Student();
        s.setCourse("Java");

        MethodType methodType = MethodType.methodType(String.class);
        MethodHandle getCourseHandle = lookup.findVirtual(clss, "getCourse", methodType);
        System.out.println(getCourseHandle.invoke(s));

        MethodType type = MethodType.methodType(void.class);
        MethodHandle noArgHandle = lookup.findConstructor(clss, type);
        Student s1 =(Student) noArgHandle.invoke();
        s1.setName("Meena");
        s1.setCourse("scala");
        System.out.println(s1.toString());

        MethodType type1 = MethodType.methodType(void.class, String.class, String.class);
        MethodHandle paraConstructor = lookup.findConstructor(clss, type1);
        Student divya= (Student)paraConstructor.invoke("Divya", "Python");
        System.out.println(divya.toString());

        MethodType methodType2 = MethodType.methodType(void.class, String.class);
        MethodHandle setNameHandle = lookup.findVirtual(clss, "setName", methodType2);
        setNameHandle.invoke(s1,"Mohit");
        System.out.println(s1.getName());

        MethodType methodType3 = MethodType.methodType(void.class, int.class);
        MethodHandle setNumberOfStudentHandle = lookup.findStatic(clss,"setNumOfStudents", methodType3);;
        setNumberOfStudentHandle.invoke(2);
        System.out.println(s1.getNumOfStudents());

        //for private menbers
        //MethodHandle findGetter = lookup.findGetter(clss, "name", String.class);
        //findGetter.invoke(s1); //This will not work ...java.lang.IllegalAccessException: member is private

        Lookup privateLookupIn = MethodHandles.privateLookupIn(clss, lookup);
        MethodHandle findGetter = privateLookupIn.findGetter(clss, "name", String.class);
        MethodHandle findSetter = privateLookupIn.findSetter(clss, "name", String.class);
        findSetter.invoke(s1, "Justin");
        System.out.println(findGetter.invoke(s1));

        //varHandles

    }
}
