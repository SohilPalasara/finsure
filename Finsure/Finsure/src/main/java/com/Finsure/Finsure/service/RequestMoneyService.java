package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.ContactDto;
import com.Finsure.Finsure.dto.RequestMoneyDto;
import com.Finsure.Finsure.dto.UserDto;
import com.Finsure.Finsure.entity.Card;
import com.Finsure.Finsure.entity.Contact;
import com.Finsure.Finsure.entity.RequestMoney;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.enums.RequestStatus;
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
    public ResponseModel requestMoney(RequestMoneyDto requestMoneyDto) {
        try {
        User requestSender = userRepository.findByUserId(requestMoneyDto.getRequestSender());
        User requestReceiver = userRepository.findByUserId(requestMoneyDto.getRequestReceiverId());

        if (requestSender == null || requestReceiver == null) {
            return new ResponseModel("error", "Sender or Receiver not found.");
        }

        RequestMoney requestMoney = requestMoneyDto.convertToEntity(requestSender, requestReceiver);
        requestMoneyRepository.save(requestMoney);
        return new ResponseModel(requestMoney, "Request sent successfully");
        } catch (Exception e) {
            return new ResponseModel("error : ", e.getMessage());
        }
    }

    public ResponseModel acceptRequest(RequestMoneyDto requestMoneyDto) {
        try {
            RequestMoney request = requestMoneyRepository.findByRequestMoneyId(requestMoneyDto.getRequestMoneyId());

            if (request == null) {
                return new ResponseModel("error", "Request not found.");
            }
            if (request.getStatus() != RequestStatus.PENDING) {
                return new ResponseModel("error", "Request is already processed.");
            }
            User sendTheRequest = request.getRequestReceiver();
            User receiveTheRequest = request.getRequestReceiver();
            double amount = request.getAmount();
            Card senderCard = cardRepository.findFirstByUser_UserId(sendTheRequest.getUserId());
            Card receiverCard = cardRepository.findFirstByUser_UserId(receiveTheRequest.getUserId());
            senderCard.setBalance(senderCard.getBalance() - amount);
            receiverCard.setBalance(receiverCard.getBalance() + amount);
            request.setStatus(RequestStatus.ACCEPTED);
            cardRepository.save(senderCard);
            cardRepository.save(receiverCard);
            requestMoneyRepository.save(request);

            return new ResponseModel(request, "Request accepted and money transferred.");
        } catch (Exception e) {
            return new ResponseModel("error", e.getMessage());
        }
    }

//    public ResponseModel getRequestByUserId(Long userId) {
////        try {
//            User user = userRepository.findByUserId(userId);
//            if (user == null) {
//                return new ResponseModel(HttpStatus.NOT_FOUND, "User does not exist");
//            }

//            List<RequestMoney> requestMoneyList = requestMoneyRepository.findByRequestSender_UserIdOrRequestReceiver_UserId(userId);
////            if (requestMoneyList.isEmpty()) {
////                return new ResponseModel(HttpStatus.NOT_FOUND, "No users found");
////            }
//            List<RequestMoneyDto> requestMoneyDtoList = requestMoneyList.stream()
//                    .map(requestMoney ->  RequestMoneyDto.convertToDto(requestMoney))
//                    .toList();

//            return new ResponseModel(requestMoneyDtoList, "request money retrieved successfully");
//        } catch (Exception e) {
//            return new ResponseModel("error : ", e.getMessage());
//        }
//
//
//    }

            public ResponseModel getAllRequestMoney () {
                try {
                    List<RequestMoney> requestMoneyList = requestMoneyRepository.findAll();

                    if (requestMoneyList.isEmpty()) {
                        return new ResponseModel(HttpStatus.NO_CONTENT, "No users found");
                    }
                    List<RequestMoneyDto> requestMoneyDtoList = requestMoneyList.stream()
                            .map(requestMoney -> RequestMoneyDto.convertToDto(requestMoney))
                            .toList();

                    return new ResponseModel(requestMoneyDtoList, "Users retrieved successfully");
                } catch (Exception e) {
                    return new ResponseModel("error : ", e.getMessage());
                }
            }
        }

