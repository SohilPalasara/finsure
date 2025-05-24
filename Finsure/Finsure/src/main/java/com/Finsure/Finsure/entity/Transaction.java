package com.Finsure.Finsure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long transactionId;


    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private Double amount;

    private String message;

    @CreationTimestamp
    private LocalDateTime timestamp;


}
