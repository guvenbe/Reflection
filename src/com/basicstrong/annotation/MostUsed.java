package com.basicstrong.annotation;

import java.lang.annotation.*;

@Documented //If annotation is dicumented
@Inherited  //If annotation is inherited
@Target({ElementType.TYPE ,ElementType.METHOD}) //Where can the annotation be used class, method etc.
@Retention(RetentionPolicy.RUNTIME) //Lifespan of annotation Source, class or runtime
public @interface MostUsed {
String value() default "Java";
}
