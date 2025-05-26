package com.Finsure.Finsure.repository;

import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile , Long> {

    List<UserProfile> findByUserId_UserId(Long userId);
}
