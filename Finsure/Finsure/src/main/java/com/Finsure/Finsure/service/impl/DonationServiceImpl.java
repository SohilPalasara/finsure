package com.Finsure.Finsure.service.impl;



import com.Finsure.Finsure.dto.DonationDto;
import com.Finsure.Finsure.entity.Card;
import com.Finsure.Finsure.entity.Donation;
import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.repository.CardRepository;
import com.Finsure.Finsure.repository.DonationRepository;
import com.Finsure.Finsure.repository.UserRepository;
import com.Finsure.Finsure.service.DonationService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {
        @Autowired
        private DonationRepository donationRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private CardRepository cardRepository;

        @Override
        public ResponseModel makeDonation(DonationDto donationDto) {
            try {
                User user = userRepository.findById(donationDto.getUserId()).orElse(null);
                Card card = cardRepository.findById(donationDto.getCardId()).orElse(null);

                if (user == null || card == null) {
                    return new ResponseModel(HttpStatus.NOT_FOUND, "User or Card not found");
                }

                if (card.getBalance() < donationDto.getAmount()) {
                    return new ResponseModel(HttpStatus.BAD_REQUEST, "Insufficient card balance");
                }

                card.setBalance(card.getBalance() - donationDto.getAmount());
                cardRepository.save(card);

                Donation donation = donationDto.convertToEntity(user, card);
                donationRepository.save(donation);

                return new ResponseModel(donation, "Donation successful");
            } catch (Exception e) {
                return new ResponseModel("error : " , e.getMessage());
            }
        }

        @Override
        public ResponseModel getUserDonations(Long userId) {
            List<Donation> donations = donationRepository.findByUser_UserId(userId);
            List<DonationDto> donationDtoList = donations.stream()
                    .map(DonationDto::convertToDto)
                    .toList();

            return new ResponseModel(donationDtoList, "User donation history");
        }
    }


