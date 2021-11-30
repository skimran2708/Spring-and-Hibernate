package com.example.spring;

public class MyApp {
    public static void main(String[] args) {
        //com.example.spring.Coach theCoach = new com.example.spring.BaseballCoach();
        Coach theCoach =new TrackCoach();
        System.out.println(theCoach.getDailyWorkout());
    }
}
