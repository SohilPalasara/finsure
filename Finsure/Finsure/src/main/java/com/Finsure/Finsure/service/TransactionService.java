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

        User sender = userRepository.findById(transactionDto.getSenderId()).orElse(null);
        User receiver = userRepository.findById(transactionDto.getReceiverId()).orElse(null);
        try {

            if (sender == null || receiver == null) {
                return new ResponseModel("error", "Sender or Receiver not found");
            }

            Card senderCard = cardRepository.findFirstByUser_UserId(sender.getUserId());
            if (senderCard == null) {
                return new ResponseModel(null, "Sender has no card.");
            }
            double amount = transactionDto.getAmount();
            if (senderCard.getBalance() < amount) {
                return new ResponseModel(null, "Insufficient balance.");
            }
            senderCard.setBalance(senderCard.getBalance() - amount);
            cardRepository.save(senderCard);

            Card receiverCard = cardRepository.findFirstByUser_UserId(receiver.getUserId());

            receiverCard.setBalance(receiverCard.getBalance() + amount);
            cardRepository.save(receiverCard);

            Transaction transaction = transactionDto.convertToEntity(sender, receiver);
            transactionRepository.save(transaction);
            return new ResponseModel(transaction, "request money successfully ");
        } catch (Exception e) {
            return  new ResponseModel("error : " , e.getMessage());
        }
    }
}
