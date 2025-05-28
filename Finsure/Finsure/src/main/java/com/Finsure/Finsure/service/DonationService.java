package com.Finsure.Finsure.service;

import com.Finsure.Finsure.dto.DonationDto;
import com.Finsure.Finsure.utills.ResponseModel;

public interface DonationService {
    ResponseModel makeDonation(DonationDto donationDto);
    ResponseModel getUserDonations(Long userId);
}
