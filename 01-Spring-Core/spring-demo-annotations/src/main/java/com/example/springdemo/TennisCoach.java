package com.example.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("singleton")
public class TennisCoach implements Coach{

    @Autowired          //Field Injection
    @Qualifier("randomFortuneService")
    private FortuneService fortuneService;

    // define my init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println(">> TennisCoach: inside of doMyStartupStuff()");
    }

    // define my destroy method
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println(">> TennisCoach: inside of doMyCleanupStuff()");
    }

    /*
    @Autowired          //Constructor Injection
    public TennisCoach(FortuneService theFortuneService){
        this.fortuneService=theFortuneService;
    }
    */

    public TennisCoach(){
        System.out.println("Inside the default Constructor");
    }

    /*
    @Autowired          //Setter Injection
    public void setFortuneService(FortuneService theFortuneService){
        System.out.println("Inside setFortuneService method");
        this.fortuneService=theFortuneService;
    }
     */

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
