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
    public ResponseModel requestMoney(RequestMoneyDto dto) {
        User sender = userRepository.findByUserId(dto.getSenderId());
        User receiver = userRepository.findByUserId(dto.getReceiverId());

        if (sender == null || receiver == null) {
            return new ResponseModel("data", "Sender or Receiver not found.");
        }
RequestStatus status = RequestStatus . pending;
        RequestMoney request = dto.convertToEntity(sender, receiver);

        requestMoneyRepository.save(request);
        return new ResponseModel(request, "Request sent successfully");
    }

    public  ResponseModel acceptRequest(Long requestId) {
        RequestMoney request = requestMoneyRepository.findByRequestId(requestId);
        if (request == null || request.) {
            return new ResponseModel("error", "Invalid or already accepted request.");
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
