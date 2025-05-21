package com.Finsure.Finsure.repository;

import com.Finsure.Finsure.entity.User;
import com.Finsure.Finsure.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile , Long> {
    User findByEmail(String email);
}
