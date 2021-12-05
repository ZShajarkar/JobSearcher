package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/public/register")
    public String viewSignUpForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "signup_form";
    }

    @GetMapping("/list_users")
    public String testYouAreLogin() {
        return "testPage";
    }

    @GetMapping("/public/login")
    public String viewLogin() {
        return "login";
    }

}
