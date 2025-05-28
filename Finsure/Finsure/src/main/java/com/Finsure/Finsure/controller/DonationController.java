package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.DonationDto;
import com.Finsure.Finsure.service.impl.DonationServiceImpl;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/v1/users")
    public class DonationController {

        @Autowired
        private DonationServiceImpl donationServiceImpl;

        @PostMapping("/donation")
        public ResponseModel makeDonation(@RequestBody DonationDto dto) {
            return donationServiceImpl.makeDonation(dto);
        }

        @GetMapping("/getUserById/{userId}")
        public ResponseModel getUserDonations(@PathVariable Long userId) {
            return donationServiceImpl.getUserDonations(userId);
        }
    }


