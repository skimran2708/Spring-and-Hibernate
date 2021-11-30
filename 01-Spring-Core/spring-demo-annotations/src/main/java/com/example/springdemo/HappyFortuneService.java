package com.example.springdemo;

import org.springframework.stereotype.Component;

@Component                  //so that spring can register
public class HappyFortuneService implements FortuneService{
    @Override
    public String getFortune() {
        return "Today is your lucky day";
    }
}
