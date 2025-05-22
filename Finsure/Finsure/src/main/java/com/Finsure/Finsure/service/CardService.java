package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.CardDto;
import com.Finsure.Finsure.dto.ContactDto;
import com.Finsure.Finsure.entity.Card;
import com.Finsure.Finsure.entity.Contact;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.repository.CardRepository;
import com.Finsure.Finsure.repository.UserRepository;
import com.Finsure.Finsure.utills.ResponseModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;
    public ResponseModel saveCard(CardDto cardDto) {
try {
    boolean accountCartExist = cardRepository.existsByAccountNumber(cardDto.getAccountNumber());

    if (accountCartExist) {
        return new ResponseModel("data", "Card already exists in the cart.");
    }
    User user = userRepository.findById(cardDto.getUserId()).orElse(null);
    if (user == null) {
        return new ResponseModel("data : ", "user Not Found :");
    }
    Card card = cardDto.convertToEntity();
    card.setUser(user);

    Card savedCard = cardRepository.save(card);
    return new ResponseModel(cardDto.convertToDto(savedCard), "registration successfully");
} catch (Exception e) {
    return new ResponseModel("error : " , e.getMessage());
}
    }

    public ResponseModel getCardsByUserId(Long userId) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return new ResponseModel("data", "User not found");
            }
            List<Card> cards = cardRepository.findByUser_UserId(userId);

            List<CardDto> cardDtoList = cards.stream()
                    .map(card -> CardDto.convertToDto(card))
                    .toList();
            return new ResponseModel(cardDtoList, "Cards fetched successfully");
        } catch (Exception e) {
            return new ResponseModel("error", e.getMessage());
        }
    }
    public ResponseModel getAllCard() {
        try {
            List<Card>cards = cardRepository.findAll();

            if (cards.isEmpty()) {
                return new ResponseModel(HttpStatus.NO_CONTENT, "No users found");
            }
            List<CardDto> cardDtoList = cards.stream()
                    .map(card->CardDto.convertToDto(card))
                    .toList();

            return new ResponseModel(cardDtoList, "Users retrieved successfully");
        } catch (Exception e) {
            return new ResponseModel("error : ", e.getMessage());
        }
    }
}
