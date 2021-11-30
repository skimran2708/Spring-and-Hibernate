package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
    public void forDAOPackage() {}

    @Before("forDAOPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n>>>> Executing @Before advice on addAccount()");
    }

    @Before("forDAOPackage()")
    public void performApiAnalytics() {
        System.out.println("\n>>>> Performing API Analytics");
    }
}
