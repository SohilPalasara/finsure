package com.Finsure.Finsure.dto;

import com.Finsure.Finsure.entity.RequestMoney;
import com.Finsure.Finsure.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestMoneyDto {
    private String name;
    private String email;
    private String description;
    private Long senderId;
    private Long receiverId;
    private LocalDate monthlyDueByDate;
    private double amount;


    public RequestMoney convertToEntity(User sender, User receiver){
        RequestMoney requestMoney = new RequestMoney();
        requestMoney.setName(this.name);
        requestMoney.setEmail(this.email);
        requestMoney.setDescription(this.description);
        requestMoney.setMonthlyDueByDate(this.monthlyDueByDate);
        requestMoney.setAmount(this.amount);
        requestMoney.setSender(sender);
        requestMoney.setReceiver(receiver);

        return requestMoney;
    }
    public static RequestMoneyDto convertToDto(RequestMoney requestMoney){
        RequestMoneyDto requestMoneyDto = new RequestMoneyDto();
        requestMoneyDto.setName(requestMoney.getName());
        requestMoneyDto.setEmail(requestMoney.getEmail());
        requestMoneyDto.setDescription(requestMoney.getDescription());
        requestMoneyDto.setMonthlyDueByDate(requestMoney.getMonthlyDueByDate());
        requestMoneyDto.setAmount(requestMoney.getAmount());

        if (requestMoney.getSender() != null) {
            requestMoneyDto.setSenderId(requestMoney.getSender().getUserId());
        }

        if (requestMoney.getReceiver() != null) {
            requestMoneyDto.setReceiverId(requestMoney.getReceiver().getUserId());
        }

        return requestMoneyDto;
    }
}
