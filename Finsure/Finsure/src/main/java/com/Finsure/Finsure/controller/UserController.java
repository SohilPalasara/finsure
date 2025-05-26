package com.Finsure.Finsure.controller;


import com.Finsure.Finsure.dto.UserDto;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.service.UserService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/users")
    public class UserController {
        @Autowired
        private UserService userService;
        @PostMapping("/signup")
        public ResponseModel signUp(@RequestBody UserDto userDto) {

            return userService.saveUser(userDto);
        }
    @PostMapping("/signIn/{phoneNumber}")
    public ResponseModel signIn(@PathVariable String phoneNumber) {

        return userService.signIn(phoneNumber);
    }
    @GetMapping("/allUsers")
    public ResponseModel getAllUsers() {
        return userService.getAllUsers();
    }
    }

