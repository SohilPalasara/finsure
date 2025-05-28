package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.CardDto;
import com.Finsure.Finsure.service.impl.CardServiceImpl;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class CardController {
    @Autowired
    private CardServiceImpl cardServiceImpl;

    @PostMapping("/card")
    public ResponseModel registerCard(@RequestBody CardDto cardDto) {
        return cardServiceImpl.saveCard(cardDto);
    }

    @GetMapping("/card/{userId}")
    public ResponseModel getCardsByUser(@PathVariable Long userId) {
        return cardServiceImpl.getCardsByUserId(userId);
    }

    @GetMapping("/getAllCard")
    public ResponseModel getAllCard() {
        return cardServiceImpl.getAllCard();
    }
}
