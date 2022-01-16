package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.services.UserService;
import com.example.demo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/public/process_register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> processRegistration(@RequestBody UserDto userDto) {
        try {
            return ResponseFactory.ok(userService.save(userDto));
        } catch (Exception e) {
            return ResponseFactory.handel((HttpClientErrorException) e);
        }

    }
}
