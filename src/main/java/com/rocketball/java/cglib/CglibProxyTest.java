package com.rocketball.java.cglib;

import com.rocketball.java.jdkproxy.JDKDynamicProxyTest;
import com.rocketball.java.jdkproxy.Target;
import com.rocketball.java.jdkproxy.TargetImp;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ConcurrentModificationException;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * cglib 和jdk动态代理速度
 * java 1.8 jdk 动态代理更快
 *
 */
public class CglibProxyTest implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start");
        Object obj = methodProxy.invokeSuper(o,objects);
        System.out.println("end");
        return obj;
    }
    public static <T extends Target> Target newProxyInstance(Class<T> tagetInterfaceClazz){
        Enhancer enhancer  = new Enhancer();
        enhancer.setSuperclass(tagetInterfaceClazz);
        enhancer.setCallback(new CglibProxyTest());
        return (Target) enhancer.create();
    }

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        Target target = new TargetImp();
        Target targetJdk =JDKDynamicProxyTest.newProxyInstance(target);
        Target targetCglib
                =  CglibProxyTest.newProxyInstance(TargetImp.class);
//        for(int i=0;i<1000;i++){
//            targetCglib.test(5);
//            targetJdk.test(5);
//        }
        long s0 = System.currentTimeMillis();
        for(int i=0;i<10;i++){
            System.out.println(targetJdk.test(i));
        }
        long s1 =  System.currentTimeMillis();
        for(int i=0;i<10;i++){
            System.out.println( targetCglib.test(i));
        }
        long s2 =  System.currentTimeMillis();
        for(int i=0;i<10;i++){
            System.out.println(target.test(i));
        }
        long s3 =  System.currentTimeMillis();
        System.out.println(s1-s0);
        System.out.println(s2-s1);
        System.out.println(s3-s2);
    }
}
