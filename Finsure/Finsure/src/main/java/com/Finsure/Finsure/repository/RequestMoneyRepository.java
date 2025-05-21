package com.Finsure.Finsure.repository;

import com.Finsure.Finsure.entity.Contact;
import com.Finsure.Finsure.entity.RequestMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestMoneyRepository extends JpaRepository<RequestMoney , Long> {
    List<RequestMoney> findByUserId_UserId(Long userId);
}
