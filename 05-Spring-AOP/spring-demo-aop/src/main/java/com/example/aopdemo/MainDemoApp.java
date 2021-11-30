package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);

        MembershipDAO theMembershipDAO = context.getBean("membershipDAO",MembershipDAO.class);

        Account myAccount = new Account();

        theAccountDAO.addAccount(myAccount, true);
        theAccountDAO.doWork();
        theMembershipDAO.addMember();
        theMembershipDAO.gotToSleep();

        context.close();
    }
}
