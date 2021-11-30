package com.example.aopdemo.aspect;

import com.example.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @After("execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJointPoint) {
        String method = theJointPoint.getSignature().toShortString();

        System.out.println("\n>>>> Executing @After (finally) on method: "+method);
    }

    @AfterThrowing (
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theException")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJointPoint, Throwable theException) {
        String method = theJointPoint.getSignature().toShortString();

        System.out.println("\n>>>> Executing @AfterThrowinging on method: "+method);

        System.out.println("\n>>>> Exception is: "+theException);
    }

    @AfterReturning (
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJointPoint, List<Account> result) {
        String method = theJointPoint.getSignature().toShortString();

        System.out.println("\n>>>> Executing @AfterReturning on method: "+method);

        System.out.println("\n>>>> Result is: "+result);

        convertAccountNamesToUpperCase(result);

        System.out.println("\n>>>> Result is after modifying return values: "+result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for(Account tempAccount: result) {
            String theUpperName = tempAccount.getName().toUpperCase();
            tempAccount.setName(theUpperName);
        }
    }

    /*
    @Before("com.example.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJointPoint) {
        System.out.println("\n>>>> Executing @Before advice on addAccount()");

        MethodSignature methodSignature = (MethodSignature) theJointPoint.getSignature();
        System.out.println("Method Signature: "+ methodSignature);

        Object args[] = theJointPoint.getArgs();
        for (Object tempArg : args) {
            System.out.println(tempArg);

            if(tempArg instanceof Account) {
                Account theAccount = (Account) tempArg;
                System.out.println("Account Name: " + theAccount.getName());
                System.out.println("Account Level: " + theAccount.getLevel());
            }
        }
    }
     */

}
