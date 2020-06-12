package com.basicstrong.annotation;

@MostUsed
public class Utility {



    void  doStuff(){
        System.out.println("Doing some stuff");
    }
    @MostUsed("Python")
    void  doStuff(String arg){
        System.out.println("Operating on Arg :" + arg);
    }
    void  doStuff(int i){
        System.out.println("Operating on i :" + i);
    }
}

class Subutilty extends Utility{

}