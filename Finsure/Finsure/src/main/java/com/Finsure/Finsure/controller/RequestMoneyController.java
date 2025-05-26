package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.RequestMoneyDto;
import com.Finsure.Finsure.service.RequestMoneyService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/RequestMoney")
public class RequestMoneyController {
    @Autowired
    private RequestMoneyService requestMoneyService;

    @PostMapping("/save/requestMoney")
    public ResponseModel requestMoney(@RequestBody RequestMoneyDto requestMoneyDto) {
        return requestMoneyService.requestMoney(requestMoneyDto);
    }
    @PostMapping("/accept")
    public ResponseModel acceptRequest(@RequestBody RequestMoneyDto requestMoneyDto) {
        return requestMoneyService.acceptRequest(requestMoneyDto);
    }
    @GetMapping("/requestMoneyByUser/{userId}")
    public ResponseModel getAllContactsForUser(@PathVariable long userId) {
        return requestMoneyService.getRequestByUserId(userId);
    }
    @GetMapping("/getAllRequestMoney")
    public ResponseModel getAllRequestMoney() {
        return requestMoneyService.getAllRequestMoney();
    }
}
