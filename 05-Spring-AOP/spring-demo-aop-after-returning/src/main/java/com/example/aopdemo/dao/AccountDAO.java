package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;

    public List<Account> findAccounts() {
        System.out.println(">>>> Executing the findAccounts()");
        List<Account> myAccounts = new ArrayList<>();

        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("Madhu", "Gold");
        Account temp3 = new Account("Luca", "Bronze");

        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }

    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass()+": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    public boolean doWork() {
        System.out.println(getClass()+": doWork");
        return false;
    }

    public String getName() {
        System.out.println(getClass()+": getName");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass()+": setName");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass()+": getServiceCode");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass()+": setServiceCode");
        this.serviceCode = serviceCode;
    }
}
