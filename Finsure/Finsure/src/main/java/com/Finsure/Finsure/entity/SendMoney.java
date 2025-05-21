package com.Finsure.Finsure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class SendMoney {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private User sender;

//        @ManyToOne
//        @JoinColumn(name = "recipient_id", nullable = false)
//        private Contact recipient;

        private double amount;
        private String currency;
        private String message;
        private LocalDateTime transactionDate;
        private String status;



    }


