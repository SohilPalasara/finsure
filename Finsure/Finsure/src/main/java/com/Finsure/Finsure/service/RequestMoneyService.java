package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.ContactDto;
import com.Finsure.Finsure.dto.RequestMoneyDto;
import com.Finsure.Finsure.dto.UserDto;
import com.Finsure.Finsure.entity.Card;
import com.Finsure.Finsure.entity.Contact;
import com.Finsure.Finsure.entity.RequestMoney;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.repository.CardRepository;
import com.Finsure.Finsure.repository.RequestMoneyRepository;
import com.Finsure.Finsure.repository.UserRepository;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestMoneyService {

    @Autowired
    private RequestMoneyRepository requestMoneyRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    public ResponseModel saveRequest(RequestMoneyDto requestMoneyDto) {


         User user = userRepository.findById(requestMoneyDto.getUserId()).orElse(null);
        try {

            if (user == null) {
                return new ResponseModel("data", "User Not Found");
            }
            Card existingCard = cardRepository.findFirstByUser_UserId(user.getUserId());
            if (existingCard != null) {
                double updatedBalance = existingCard.getBalance() + requestMoneyDto.getAmount();
                existingCard.setBalance(updatedBalance);
                cardRepository.save(existingCard);
            } else {
                Card newCard = new Card();
                newCard.setUser(user);
                newCard.setBalance(requestMoneyDto.getAmount());
                cardRepository.save(newCard);
            }
            RequestMoney requestMoney = requestMoneyDto.convertToEntity(user);
            requestMoneyRepository.save(requestMoney);
            requestMoney.setUserId(user);

            return new ResponseModel(requestMoney, "request money successfully ");
        } catch (Exception e) {
            return  new ResponseModel("error : " , e.getMessage());
        }

    }

    public ResponseModel getRequestByUserId(Long userId) {
        try {
            User user = userRepository.findByUserId(userId);
            if (user == null) {
                return new ResponseModel(HttpStatus.NOT_FOUND, "User does not exist");
            }

            List<RequestMoney> requestMoneyList = requestMoneyRepository.findByUserId_UserId(userId);
            if (requestMoneyList.isEmpty()) {
                return new ResponseModel(HttpStatus.NOT_FOUND, "No users found");
            }
            List<RequestMoneyDto> requestMoneyDtoList = requestMoneyList.stream()
                    .map(requestMoney ->  RequestMoneyDto.convertToDto(requestMoney))
                    .toList();

            return new ResponseModel(requestMoneyDtoList, "request money retrieved successfully");
        } catch (Exception e) {
            return new ResponseModel("error : ", e.getMessage());
        }


    }

    public ResponseModel getAllRequestMoney() {
        try {
            List<RequestMoney> requestMoneyList = requestMoneyRepository.findAll();

            if (requestMoneyList.isEmpty()) {
                return new ResponseModel(HttpStatus.NO_CONTENT, "No users found");
            }
            List<RequestMoneyDto> requestMoneyDtoList = requestMoneyList.stream()
                    .map(requestMoney->RequestMoneyDto.convertToDto(requestMoney))
                    .toList();

            return new ResponseModel(requestMoneyDtoList, "Users retrieved successfully");
        } catch (Exception e) {
            return new ResponseModel("error : ", e.getMessage());
        }
    }
}
