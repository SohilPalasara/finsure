package com.Finsure.Finsure.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;

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
    private double balance;
    private int dailyLimit;
    private String status;
    @CreationTimestamp
    private LocalDate created_at;
    @ManyToOne
    private User user;
}

