package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.SignUpUserRequestDto;
import com.example.demo.services.UserService;
import com.example.demo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/public/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/process_register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> processRegistration(@Valid @RequestBody SignUpUserRequestDto userDto) {
        try {
            return ResponseFactory.ok(userService.save(userDto));
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }

    @PostMapping(path = "/sign_in", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> processRegistration(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        try {
            return ResponseFactory.ok(userService.authenticateUser(loginRequestDto));
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }
}
