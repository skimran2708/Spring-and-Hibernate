package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AroundDemoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService",TrafficFortuneService.class);

        System.out.println("\n\nMain Program: AroundDemoApp");
        System.out.println("Calling getFortune");
        String data = theFortuneService.getFortune();
        System.out.println("\nMy Fortune is: "+ data);
        System.out.println("Finished");

        context.close();
    }
}
