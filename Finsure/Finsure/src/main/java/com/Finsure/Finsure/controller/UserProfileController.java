package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.UserProfileDto;
import com.Finsure.Finsure.service.impl.UserProfileServiceImpl;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/Profile")
public class UserProfileController {
    @Autowired
    private UserProfileServiceImpl userProfileServiceImpl;

    @PostMapping("/saveProfile")
    public ResponseModel  saveProfile (@RequestBody UserProfileDto userProfileDto){
        return userProfileServiceImpl.saveProfile(userProfileDto);
    }

    @GetMapping("/getProfileByUserId/{userId}")
    public ResponseModel  getContactsByUserId (@PathVariable long userId){
        return userProfileServiceImpl.getContactsByUserId(userId);
    }
}
