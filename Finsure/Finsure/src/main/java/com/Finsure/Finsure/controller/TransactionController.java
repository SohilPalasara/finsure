package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.TransactionDto;
import com.Finsure.Finsure.service.TransactionService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/saveTransaction")
    public ResponseModel saveTransaction(@RequestBody TransactionDto transactionDto) {

        return transactionService.saveTransaction(transactionDto);
    }
}