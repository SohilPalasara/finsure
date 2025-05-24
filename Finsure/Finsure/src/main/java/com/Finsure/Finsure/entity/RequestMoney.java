package com.Finsure.Finsure.entity;

import com.Finsure.Finsure.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class RequestMoney {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long requestMoneyId ;

    private String name;
    private String email;
    private String description;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User requestSender;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User requestReceiver;


    private LocalDate monthlyDueByDate;
    private double amount;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
