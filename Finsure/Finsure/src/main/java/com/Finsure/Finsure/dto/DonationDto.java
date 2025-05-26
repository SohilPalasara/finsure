package com.Finsure.Finsure.dto;

import com.Finsure.Finsure.entity.Card;
import com.Finsure.Finsure.entity.Donation;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.enums.DonationFrequency;
import lombok.Data;

    @Data
    public class DonationDto {
        private Double amount;
        private Long userId;
        private Long cardId;
        private DonationFrequency frequency;


        public Donation convertToEntity(User user, Card card) {
            Donation donation = new Donation();
            donation.setAmount(amount);
            donation.setFrequency(frequency);
            donation.setUser(user);
            donation.setCard(card);
            return donation;
        }

        public static DonationDto convertToDto(Donation donation) {
            DonationDto dto = new DonationDto();
            dto.setAmount(donation.getAmount());
            dto.setFrequency(donation.getFrequency());
            dto.setUserId(donation.getUser().getUserId());
            dto.setCardId(donation.getCard().getCardId());
            return dto;
        }

}
