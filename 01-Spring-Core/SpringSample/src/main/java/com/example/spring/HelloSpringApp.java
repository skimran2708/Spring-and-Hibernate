package com.example.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {
    public static void main(String[] args) {
        // load the spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // retrieve bean from spring container
        Coach theCoach= context.getBean("myCoach",Coach.class);

        // call methods on the bean --ioc
        System.out.println(theCoach.getDailyWorkout());

        // let's call our new method for fortunes --di
        System.out.println(theCoach.getDailyFortune());

        context.close();
    }
}
