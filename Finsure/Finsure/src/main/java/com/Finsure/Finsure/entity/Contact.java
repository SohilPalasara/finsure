package com.Finsure.Finsure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long contactId;

    private  String name;
    private String phoneNo;

    private boolean isFavorite = false;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private  User user;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreationTimestamp
    private LocalDate created_at;

    public Contact(String name, String phoneNo , boolean isFavorite ) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.isFavorite = isFavorite;

    }
}
