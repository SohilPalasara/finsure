package com.Finsure.Finsure.repository;

import com.Finsure.Finsure.entity.Transaction;
import com.Finsure.Finsure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction , Long> {


    List<Transaction> findBySender_UserIdOrReceiver_UserId(Long senderId, Long receiverId);


}