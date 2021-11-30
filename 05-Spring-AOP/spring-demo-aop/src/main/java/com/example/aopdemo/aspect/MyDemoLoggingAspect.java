package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    //for any class
    //@Before("execution(public void addAccount())")

    //for specific class
    //@Before("execution(public void com.example.aopdemo.dao.AccountDAO.addAccount())")

    //match method with any class starting with add
    //@Before("execution(public void add*())")

    //match method with specific return type & modifier is optional
    //@Before("execution(void add*())")

    //match with any return type
    //@Before("execution(* add*())")

    //match with specific param type
    //@Before("execution(* add*(com.example.aopdemo.Account))")

    //match with any param type
    //@Before("execution(* add*(com.example.aopdemo.Account, ..))")

    //match methods with any package
    @Before("execution(* com.example.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n>>>> Executing @Before advice on addAccount()");
    }
}
