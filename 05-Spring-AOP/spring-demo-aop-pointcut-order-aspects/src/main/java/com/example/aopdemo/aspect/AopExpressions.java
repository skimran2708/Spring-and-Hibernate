package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
    public void forDAOPackage() {}

    @Pointcut("execution(* com.example.aopdemo.dao.*.get*(..))")
    public void getter() {}

    @Pointcut("execution(* com.example.aopdemo.dao.*.set*(..))")
    public void setter() {}

    //include package and exclude getter and setter
    @Pointcut("forDAOPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}
}
