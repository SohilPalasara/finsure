package com.Finsure.Finsure.dto;

import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.entity.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserProfileDto {

    private String FullName;
    private String email;
    private String phoneNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long userId;
    private String address;

    public UserProfile convertToEntity() {
        UserProfile userProfile = new UserProfile();

        userProfile.setFullName(this.FullName);
        userProfile.setEmail(this.email);
        userProfile.setPhoneNumber(this.phoneNumber);
        userProfile.setAddress(this.address);

        return userProfile;
    }

    public static UserProfileDto convertToDto(UserProfile userProfile) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setFullName(userProfile.getFullName());
        userProfileDto.setEmail(userProfile.getEmail());
        userProfileDto.setPhoneNumber(userProfile.getPhoneNumber());
        userProfileDto.setAddress(userProfile.getAddress());
        userProfileDto.setUserId(userProfile.getUserId().getUserId());

        return userProfileDto;
    }

}
