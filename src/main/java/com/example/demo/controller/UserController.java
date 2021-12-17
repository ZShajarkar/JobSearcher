package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/public/process_register", consumes = "application/json", produces = "application/json")
    public String processRegisteration(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return "register_sucess";
    }
}
