package com.Finsure.Finsure.dto;


import com.Finsure.Finsure.entity.Transaction;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.entity.UserProfile;
import lombok.Data;

@Data
public class TransactionDto {

    private Double amount;

    private String message;

    private Long senderId;
    private Long receiverId;

    public Transaction convertToEntity(User sender, User receiver) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setMessage(message);
        transaction.setSenderId(sender);
        transaction.setReceiverId(receiver);
        return transaction;
    }


    public static TransactionDto convertToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setMessage(transaction.getMessage());
        if (transaction.getSenderId() != null) {
            transactionDto.setSenderId(transaction.getSenderId().getUserId());
        }

        if (transaction.getReceiverId() != null) {
            transactionDto.setReceiverId(transaction.getReceiverId().getUserId());
        }

        return transactionDto;
    }

}
