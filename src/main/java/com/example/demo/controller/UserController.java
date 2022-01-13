package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.response.ResponseMessage;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseMessage> processRegistration(@RequestBody UserDto userDto){
        try {
            userService.save(userDto);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("یوزر با موفقیت ثبت شد"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage()));
        }

    }
}
