package com.example.springboot.artgallery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/loginPage")
    public String loginPage() {

        return "login-page";

    }

    // create a mapping for "/hello"

    @GetMapping("/home")
    public String sayHello(Model theModel) {

        theModel.addAttribute("theDate", new java.util.Date());

        return "helloworld";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }




}







