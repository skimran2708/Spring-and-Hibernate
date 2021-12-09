package com.example.springboot.artgallery.controller;

import com.example.springboot.artgallery.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping("/loginPage")
    public String loginPage() {

        return "login-page";

    }

    // create a mapping for "/home"
    @GetMapping("/home")
    public String homePage(Model theModel) {

        theModel.addAttribute("theDate", new java.util.Date());

        return "home-page";
    }

    @GetMapping("/error")
    public String error() {
        return "error-page";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        throw new MyException("Access Denied");
    }

}







