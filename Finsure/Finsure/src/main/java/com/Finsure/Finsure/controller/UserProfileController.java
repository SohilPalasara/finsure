package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.UserProfileDto;
import com.Finsure.Finsure.service.UserProfileService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/Profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/saveProfile")
    public ResponseModel  saveProfile (@RequestBody UserProfileDto userProfileDto){
        return userProfileService.saveProfile(userProfileDto);
    }

    @GetMapping("/getProfileByUserId/{userId}")
    public ResponseModel  getContactsByUserId (@PathVariable long userId){
        return userProfileService.getContactsByUserId(userId);
    }
}
