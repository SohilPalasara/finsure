package com.Finsure.Finsure.entity;

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
    private User sender;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User receiver;


    private LocalDate monthlyDueByDate;
    private double amount;
}
