package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.ContactDto;
import com.Finsure.Finsure.dto.UserDto;
import com.Finsure.Finsure.dto.UserProfileDto;

import com.Finsure.Finsure.entity.Contact;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.entity.UserProfile;
import com.Finsure.Finsure.repository.UserProfileRepository;
import com.Finsure.Finsure.repository.UserRepository;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseModel saveProfile(UserProfileDto userProfileDto){
        try {
            User user = userRepository.findByUserId(userProfileDto.getUserId());
            if (user == null) {
                return new ResponseModel(HttpStatus.OK, user + "userNotFound");
            }
            UserProfile userProfile = userProfileDto.convertToEntity();
            userProfile.setUserId(user);
            userProfileRepository.save(userProfile);
            UserProfileDto responseDto = UserProfileDto.convertToDto(userProfile);
            return new ResponseModel(responseDto, "profile successfully");

        } catch (Exception e) {
            return new ResponseModel("error",e.getMessage());
        }
    }

    public ResponseModel getContactsByUserId(Long userId) {
        try {
            User user = userRepository.findByUserId(userId);
            if (user == null) {
                return new ResponseModel(HttpStatus.NOT_FOUND, "User does not exist");
            }

            List<UserProfile> userProfiles = userProfileRepository.findByUserId_UserId(userId);
            if (userProfiles.isEmpty()) {
                return new ResponseModel(HttpStatus.NOT_FOUND, "No users found");
            }
            List<UserProfileDto> userProfileDtoList = userProfiles.stream()
                    .map(userProfile ->  UserProfileDto.convertToDto(userProfile))
                    .toList();

            return new ResponseModel(userProfileDtoList, "Contacts retrieved successfully");
        } catch (Exception e) {
            return new ResponseModel("error : ", e.getMessage());
        }
    }
}
