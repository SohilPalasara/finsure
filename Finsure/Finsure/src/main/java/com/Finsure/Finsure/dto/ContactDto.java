package com.Finsure.Finsure.dto;


import com.Finsure.Finsure.entity.Contact;
import com.Finsure.Finsure.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContactDto {

    private  String name;
    private String phoneNo;
    private long userId;



    public Contact convertToEntity() {
        return new Contact(this.name, this.phoneNo);
    }

    public static ContactDto convertToDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setName(contact.getName());
        contactDto.setPhoneNo(contact.getPhoneNo());
        contactDto.setUserId(contact.getUser().getUserId());

        return contactDto;
    }
}
