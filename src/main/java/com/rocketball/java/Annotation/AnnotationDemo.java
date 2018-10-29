package com.rocketball.java.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //方法
@Retention(RetentionPolicy.RUNTIME)//运行期
public @interface AnnotationDemo {

}
