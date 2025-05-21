package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.ContactDto;
import com.Finsure.Finsure.service.ContactService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class    ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/savedContact")
    public ResponseModel savedContact(@RequestBody ContactDto contactDto){
        return  contactService.contactSave(contactDto);
    }
    @GetMapping("/contacts/{userId}")
    public ResponseModel getAllContactsForUser(@PathVariable long userId) {
        return contactService.getContactsByUserId(userId);

    }

    @GetMapping("/getAllContact")
    public ResponseModel getAllContact() {
        return contactService.getAllContact();
    }
}
