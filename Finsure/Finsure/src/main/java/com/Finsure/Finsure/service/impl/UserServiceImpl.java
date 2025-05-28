package com.Finsure.Finsure.service.impl;

import com.Finsure.Finsure.dto.UserDto;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.repository.UserRepository;
import com.Finsure.Finsure.service.UserService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
    public class UserServiceImpl implements UserService {
        @Autowired
        private UserRepository userRepository;

        @Override
        public ResponseModel saveUser(UserDto userDto) {
            try {
                User exist = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
                if (exist != null) {
                    return new ResponseModel(HttpStatus.OK, "User already exists");
                }
                User user = userDto.convertToEntity();
                userRepository.save(user);

                return new ResponseModel(user, "User registered successfully");
            } catch (Exception e) {
                return new ResponseModel("error : ",e.getMessage());            }
        }

        @Override
        public ResponseModel signIn(String phoneNumber) {
            User exist = userRepository.findByPhoneNumber(phoneNumber);
            if (exist == null) {
                return new ResponseModel(HttpStatus.OK, "User not found with please signup");
            }
            return new ResponseModel(HttpStatus.OK, "Login successful with phone number");
        }

        @Override
        public ResponseModel getAllUsers() {
            try {
                List<User> users = userRepository.findAll();

                if (users.isEmpty()) {
                    return new ResponseModel(HttpStatus.NOT_FOUND, "No users found");
                }
                List<UserDto> userDtoList = users.stream()
                        .map(user->UserDto.convertToDto(user))
                        .toList();

                return new ResponseModel(userDtoList, "Users retrieved successfully");
            } catch (Exception e) {
                return new ResponseModel("error : ", e.getMessage());
            }
        }

    }

