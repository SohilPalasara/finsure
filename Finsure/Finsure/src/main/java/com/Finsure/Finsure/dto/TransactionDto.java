package com.Finsure.Finsure.dto;


import com.Finsure.Finsure.entity.Transaction;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.entity.UserProfile;
import lombok.Data;

@Data
public class TransactionDto {

    private Double amount;

    private String message;

    private Long user;

    public Transaction convertToEntity(User user) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setMessage(message);
        transaction.setUser(user);
        return transaction;
    }


    public static TransactionDto convertToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setMessage(transaction.getMessage());
        transactionDto.setUser(transaction.getUser().getUserId());

        return transactionDto;
    }

}
