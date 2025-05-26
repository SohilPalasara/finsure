package com.Finsure.Finsure.entity;
import com.Finsure.Finsure.enums.DonationFrequency;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
@Entity
@Data
public class Donation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long donationId;

        private Double amount;

        private DonationFrequency frequency;

        @ManyToOne
        private Card card;

        @ManyToOne
        private User user;

        @CreationTimestamp
        private LocalDateTime timestamp;


}
