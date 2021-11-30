package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
    private void forDAOPackage() {}

    @Pointcut("execution(* com.example.aopdemo.dao.*.get*(..))")
    private void getter() {}

    @Pointcut("execution(* com.example.aopdemo.dao.*.set*(..))")
    private void setter() {}

    //include package and exclude getter and setter
    @Pointcut("forDAOPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {}


    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n>>>> Executing @Before advice on addAccount()");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n>>>> Performing API Analytics");
    }
}
