package com.Finsure.Finsure.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardId;

    private long accountNumber;
    private int expiredDate;
    private int cvv;
    private String cardHolderName;

    @ManyToOne
    private User user;

    private double balance;
    private int dailyLimit;
    private String status;
    @CreationTimestamp
    private LocalDate created_at;


}

