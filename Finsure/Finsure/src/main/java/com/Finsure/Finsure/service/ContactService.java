package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.ContactDto;
import com.Finsure.Finsure.utills.ResponseModel;

public interface ContactService {
    ResponseModel contactSave(ContactDto contactDto);
    ResponseModel getContactsByUserId(Long userId);
    ResponseModel getAllContact();
    ResponseModel FavoriteStatus(Long contactId, boolean isFavorite);
    ResponseModel getFavoriteContacts(Long userId);
}
