package com.Finsure.Finsure.controller;


import com.Finsure.Finsure.dto.UserDto;
import com.Finsure.Finsure.service.impl.UserServiceImpl;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/signup")
    public ResponseModel signUp(@RequestBody UserDto userDto) {

        return userServiceImpl.saveUser(userDto);
    }

    @PostMapping("/signIn/{phoneNumber}")
    public ResponseModel signIn(@PathVariable String phoneNumber) {

        return userServiceImpl.signIn(phoneNumber);
    }

    @GetMapping("/allUsers")
    public ResponseModel getAllUsers() {
        return userServiceImpl.getAllUsers();
    }
}

