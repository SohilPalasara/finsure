package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.UserProfileDto;
import com.Finsure.Finsure.utills.ResponseModel;

public interface UserProfileService {
    ResponseModel saveProfile(UserProfileDto userProfileDto);
    ResponseModel getContactsByUserId(Long userId);
}
