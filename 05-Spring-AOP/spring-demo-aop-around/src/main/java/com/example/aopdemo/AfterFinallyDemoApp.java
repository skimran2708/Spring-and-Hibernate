package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterFinallyDemoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);

        List<Account> theAccounts = null;
        try {
            boolean tripWire = false;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        }
        catch(Exception exception)
        {
            System.out.println("\n\nMain Program: Caught Exception: "+exception);
        }

        System.out.println("\n\nMain Program: AfterFinallyDemoApp");
        System.out.println("----");
        System.out.println(theAccounts);
        System.out.println("\n");

        context.close();
    }
}
