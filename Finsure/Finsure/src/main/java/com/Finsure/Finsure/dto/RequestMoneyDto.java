package com.Finsure.Finsure.dto;

import com.Finsure.Finsure.entity.RequestMoney;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.enums.RequestStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestMoneyDto {
    private Long requestMoneyId;
    private String name;
    private String email;
    private String description;
    private Long requestSender;
    private Long requestReceiverId;
    private LocalDate monthlyDueByDate;
    private double amount;
    private RequestStatus status;



    public RequestMoney convertToEntity(User requestSender, User requestReceiver){
        RequestMoney requestMoney = new RequestMoney();
        requestMoney.setName(this.name);
        requestMoney.setEmail(this.email);
        requestMoney.setDescription(this.description);
        requestMoney.setMonthlyDueByDate(this.monthlyDueByDate);
        requestMoney.setAmount(this.amount);
        requestMoney.setRequestSender(requestSender);
        requestMoney.setRequestReceiver(requestReceiver);
        requestMoney.setStatus(RequestStatus.PENDING);

        return requestMoney;
    }
    public static RequestMoneyDto convertToDto(RequestMoney requestMoney){
        RequestMoneyDto requestMoneyDto = new RequestMoneyDto();
        requestMoneyDto.setRequestMoneyId(requestMoney.getRequestMoneyId());
        requestMoneyDto.setName(requestMoney.getName());
        requestMoneyDto.setEmail(requestMoney.getEmail());
        requestMoneyDto.setDescription(requestMoney.getDescription());
        requestMoneyDto.setMonthlyDueByDate(requestMoney.getMonthlyDueByDate());
        requestMoneyDto.setAmount(requestMoney.getAmount());
        requestMoneyDto.setStatus(requestMoney.getStatus());


        if (requestMoney.getRequestSender() != null) {
            requestMoneyDto.setRequestSender(requestMoney.getRequestSender().getUserId());
        }

        if (requestMoney.getRequestReceiver() != null) {
            requestMoneyDto.setRequestReceiverId(requestMoney.getRequestReceiver().getUserId());
        }

        return requestMoneyDto;
    }
}
