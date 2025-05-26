package com.Finsure.Finsure.repository;

import com.Finsure.Finsure.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {
    List<Donation> findByUser_UserId(Long userId);
}
