package com.Finsure.Finsure.dto;

import com.Finsure.Finsure.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String fullName;

    private String phoneNumber;

    public UserDto(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public User convertToEntity() {
        return new User(this.fullName, this.phoneNumber);
    }
    public static UserDto convertToDto(User user) {
        return new UserDto(user.getFullName(), user.getPhoneNumber());
    }

}
