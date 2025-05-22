package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.TransactionDto;
import com.Finsure.Finsure.entity.Card;
import com.Finsure.Finsure.entity.RequestMoney;
import com.Finsure.Finsure.entity.Transaction;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.repository.CardRepository;
import com.Finsure.Finsure.repository.TransactionRepository;
import com.Finsure.Finsure.repository.UserRepository;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    public ResponseModel saveTransaction(TransactionDto transactionDto){

        User user = userRepository.findById(transactionDto.getUser()).orElse(null);

        try {

            if (user == null) {
                return new ResponseModel("data", "User Not Found");
            }
            Card existingCard = cardRepository.findFirstByUser_UserId(user.getUserId());
            if (existingCard != null) {
                double updatedBalance = existingCard.getBalance() + transactionDto.getAmount();
                existingCard.setBalance(updatedBalance);
                cardRepository.save(existingCard);
            } else {
                Card newCard = new Card();
                newCard.setUser(user);
                newCard.setBalance(transactionDto.getAmount());
                cardRepository.save(newCard);
            }
            Transaction transaction = transactionDto.convertToEntity(user);
            transactionRepository.save(transaction);
            transaction.setUser(user);

            return new ResponseModel(transaction, "request money successfully ");
        } catch (Exception e) {
            return  new ResponseModel("error : " , e.getMessage());
        }
    }
}
