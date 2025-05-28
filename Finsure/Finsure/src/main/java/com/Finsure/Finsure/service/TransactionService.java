package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.TransactionDto;
import com.Finsure.Finsure.utills.ResponseModel;

public interface TransactionService {
    ResponseModel saveTransaction(TransactionDto transactionDto);
    ResponseModel getTransactionHistory(Long userId);
}
