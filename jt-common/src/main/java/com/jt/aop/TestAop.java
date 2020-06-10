package com.jt.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect //代表是个AOP
//@Component  //交给spring容器管理
public class TestAop {


    //设置要代理的类
//    @Pointcut("within(com.jt.com.jt.service.CatServiceImpl)")
    @Pointcut("bean(catServiceImpl)")
    public void testAopa(){
        System.out.println("哈哈哈");
    }

    @Before("testAopa()")
    public void  before(){
        System.out.println("前置对象");
    }
}
