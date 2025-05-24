package com.Finsure.Finsure.repository;

import com.Finsure.Finsure.entity.Card;
import com.Finsure.Finsure.entity.RequestMoney;
import com.Finsure.Finsure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    boolean existsByAccountNumber(long accountNumber);
    List<Card> findByUser_UserId(Long userId);
    Card findFirstByUser_UserId(Long userId);


}
