package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.TransactionDto;
import com.Finsure.Finsure.service.impl.TransactionServiceImpl;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @PostMapping("/saveTransaction")
    public ResponseModel saveTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionServiceImpl.saveTransaction(transactionDto);
    }

    @GetMapping("/getTransactionHistory/{userId}")
    public ResponseModel getTransactionHistory(@PathVariable Long userId) {
        return transactionServiceImpl.getTransactionHistory(userId);
    }

}