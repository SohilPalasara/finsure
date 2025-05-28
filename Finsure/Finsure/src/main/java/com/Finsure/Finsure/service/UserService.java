package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.UserDto;
import com.Finsure.Finsure.utills.ResponseModel;

public interface UserService {
    ResponseModel saveUser(UserDto userDto);
    ResponseModel signIn(String phoneNumber);
    ResponseModel getAllUsers();
}
