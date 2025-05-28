package com.Finsure.Finsure.service.impl;

import com.Finsure.Finsure.dto.ContactDto;
import com.Finsure.Finsure.entity.Contact;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.repository.ContactRepository;
import com.Finsure.Finsure.repository.UserRepository;
import com.Finsure.Finsure.service.ContactService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseModel contactSave(ContactDto contactDto) {
        try {
            User user = userRepository.findByUserId(contactDto.getUserId());
            if (user == null) {
                return new ResponseModel(HttpStatus.NOT_FOUND, "User does not exist");
            }

            Contact contact = contactDto.convertToEntity();
            contact.setUser(user);
            contactRepository.save(contact);

            return new ResponseModel(contact, "Contact saved successfully");
        } catch (Exception e) {
            return new ResponseModel("error : ", e.getMessage());
        }
    }

    @Override
    public ResponseModel getContactsByUserId(Long userId) {
        try {
            User user = userRepository.findByUserId(userId);
            if (user == null) {
                return new ResponseModel(HttpStatus.NOT_FOUND, "User does not exist");
            }

            List<Contact> contacts = contactRepository.findByUser_UserId(userId);
            if (contacts.isEmpty()) {
                return new ResponseModel(HttpStatus.NOT_FOUND, "No users found");
            }
            List<ContactDto> contactDtoList = contacts.stream()
                    .map(contact -> ContactDto.convertToDto(contact))
                    .toList();

            return new ResponseModel(contactDtoList, "Contacts retrieved successfully");
        } catch (Exception e) {
            return new ResponseModel("error : ", e.getMessage());
        }
    }

    @Override
    public ResponseModel getAllContact() {
        try {
            List<Contact> contacts = contactRepository.findAll();

            if (contacts.isEmpty()) {
                return new ResponseModel(HttpStatus.NO_CONTENT, "No users found");
            }
            List<ContactDto> contactDtoList = contacts.stream()
                    .map(contact -> ContactDto.convertToDto(contact))
                    .toList();

            return new ResponseModel(contactDtoList, "Users retrieved successfully");
        } catch (Exception e) {
            return new ResponseModel("error : ", e.getMessage());
        }
    }
//
//    public ResponseModel markContactAsFavorite(Long contactId) {
//        try {
//            Contact contact = contactRepository.findContactByContactId(contactId);
//            if (contact == null) {
//                return new ResponseModel(HttpStatus.NOT_FOUND, "Contact not found");
//            }
//            contact.setFavorite(true);
//            contactRepository.save(contact);
//
//            return new ResponseModel(ContactDto.convertToDto(contact), "Marked as favorite");
//        } catch (Exception e) {
//            return new ResponseModel("error", e.getMessage());
//        }
//    }

    @Override
    public ResponseModel FavoriteStatus(Long contactId, boolean isFavorite) {
        try {
            Contact contact = contactRepository.findContactByContactId(contactId);
            if (contact == null) {
                return new ResponseModel(HttpStatus.NOT_FOUND, "Contact not found");
            }

            contact.setFavorite(isFavorite);
            contactRepository.save(contact);

            return new ResponseModel(ContactDto.convertToDto(contact), "Favorite status updated");
        } catch (Exception e) {
            return new ResponseModel("error", e.getMessage());
        }
    }

    @Override
    public ResponseModel getFavoriteContacts(Long userId) {
        try {
            List<Contact> contacts = contactRepository.findByUser_UserIdAndIsFavoriteTrue(userId);

            List<ContactDto> contactDtoList = contacts.stream()
                    .map(contact -> ContactDto.convertToDto(contact))
                    .toList();
            return new ResponseModel(contactDtoList, "Favorite contacts retrieved");
        } catch (Exception e) {
            return new ResponseModel("error", e.getMessage());
        }
    }


}
