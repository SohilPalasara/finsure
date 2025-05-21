package com.Finsure.Finsure.dto;

import com.Finsure.Finsure.entity.Card;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CardDto {
    private long accountNumber;
    private int expiredDate;
    private int cvv;
    private String cardHolderName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int balance;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int dailyLimit;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long userId;

    public CardDto(long accountNumber, int expiredDate, int cvv, String cardHolderName) {
        this.accountNumber = accountNumber;
        this.expiredDate = expiredDate;
        this.cvv = cvv;
        this.cardHolderName = cardHolderName;
    }

    public Card convertToEntity() {
        Card card = new Card();
        card.setAccountNumber(this.accountNumber);
        card.setExpiredDate(this.expiredDate);
        card.setCvv(this.cvv);
        card.setCardHolderName(this.cardHolderName);
        card.setBalance(this.balance);
        card.setDailyLimit(this.dailyLimit);
        card.setStatus(this.status);
        return card;
    }

    public static CardDto convertToDto(Card card) {
        return new CardDto(card.getAccountNumber(), card.getExpiredDate(), card.getCvv(), card.getCardHolderName());
    }


}