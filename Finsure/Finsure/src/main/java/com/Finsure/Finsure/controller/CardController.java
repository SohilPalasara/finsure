package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.CardDto;
import com.Finsure.Finsure.service.CardService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class CardController {
    @Autowired
    private CardService cardService;
    @PostMapping("/card")
    public ResponseModel registerCard(@RequestBody CardDto cardDto) {
        return cardService.saveCard(cardDto);
    }
    @GetMapping("/card/{userId}")
    public ResponseModel getCardsByUser(@PathVariable Long userId) {
        return cardService.getCardsByUserId(userId);
    }

    @GetMapping("/getAllCard")
    public ResponseModel getAllCard() {
        return cardService.getAllCard();
    }
}
