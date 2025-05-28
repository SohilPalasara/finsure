package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.ContactDto;
import com.Finsure.Finsure.service.impl.ContactServiceImpl;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class    ContactController {
    @Autowired
    private ContactServiceImpl contactServiceImpl;

    @PostMapping("/savedContact")
    public ResponseModel savedContact(@RequestBody ContactDto contactDto){
        return  contactServiceImpl.contactSave(contactDto);
    }
    @GetMapping("/contacts/{userId}")
    public ResponseModel getAllContactsForUser(@PathVariable long userId) {
        return contactServiceImpl.getContactsByUserId(userId);

    }

    @GetMapping("/getAllContact")
    public ResponseModel getAllContact() {
        return contactServiceImpl.getAllContact();
    }

    @PutMapping("/contact/{contactId}/favorite")
    public ResponseModel updateFavoriteStatus(@PathVariable Long contactId, @RequestParam boolean isFavorite) {
        return contactServiceImpl.FavoriteStatus(contactId, isFavorite);
    }

    @GetMapping("/contacts/{userId}/favorites")
    public ResponseModel getFavoriteContacts(@PathVariable Long userId) {
        return contactServiceImpl.getFavoriteContacts(userId);
    }



}
