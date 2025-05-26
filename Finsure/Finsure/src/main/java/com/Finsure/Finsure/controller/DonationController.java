package com.Finsure.Finsure.controller;

import com.Finsure.Finsure.dto.DonationDto;
import com.Finsure.Finsure.service.DonationService;
import com.Finsure.Finsure.utills.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/users")
    public class DonationController {

        @Autowired
        private DonationService donationService;

        @PostMapping("/donation")
        public ResponseModel makeDonation(@RequestBody DonationDto dto) {
            return donationService.makeDonation(dto);
        }

        @GetMapping("/getUserById/{userId}")
        public ResponseModel getUserDonations(@PathVariable Long userId) {
            return donationService.getUserDonations(userId);
        }
    }


