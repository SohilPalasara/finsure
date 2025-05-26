package com.Finsure.Finsure.repository;

import com.Finsure.Finsure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByPhoneNumber(String phoneNumber);

    User findByUserId(long userId);


}

