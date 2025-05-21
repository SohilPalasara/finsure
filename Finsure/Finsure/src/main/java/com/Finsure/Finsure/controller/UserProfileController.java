package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.UserProfileDto;
import com.Finsure.Finsure.service.UserProfileService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usersProfile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @PostMapping("/saveProfile")
    public ResponseModel  saveProfile (@RequestBody UserProfileDto userProfileDto){
        return userProfileService.saveProfile(userProfileDto);
    }
}
