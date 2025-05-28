package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.RequestMoneyDto;
import com.Finsure.Finsure.utills.ResponseModel;

public interface RequestMoneyService {
    ResponseModel requestMoney(RequestMoneyDto requestMoneyDto);
    ResponseModel acceptRequest(RequestMoneyDto requestMoneyDto);
    ResponseModel getRequestByUserId(Long userId);
    ResponseModel getAllRequestMoney();
}
