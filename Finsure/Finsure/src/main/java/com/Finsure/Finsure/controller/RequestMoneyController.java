package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.RequestMoneyDto;
import com.Finsure.Finsure.service.impl.RequestMoneyServiceImpl;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/RequestMoney")
public class RequestMoneyController {
    @Autowired
    private RequestMoneyServiceImpl requestMoneyServiceImpl;

    @PostMapping("/save/requestMoney")
    public ResponseModel requestMoney(@RequestBody RequestMoneyDto requestMoneyDto) {
        return requestMoneyServiceImpl.requestMoney(requestMoneyDto);
    }
    @PostMapping("/accept")
    public ResponseModel acceptRequest(@RequestBody RequestMoneyDto requestMoneyDto) {
        return requestMoneyServiceImpl.acceptRequest(requestMoneyDto);
    }
    @GetMapping("/requestMoneyByUserId/{userId}")
    public ResponseModel getAllContactsForUser(@PathVariable long userId) {
        return requestMoneyServiceImpl.getRequestByUserId(userId);
    }
    @GetMapping("/getAllRequestMoney")
    public ResponseModel getAllRequestMoney() {
        return requestMoneyServiceImpl.getAllRequestMoney();
    }
}
