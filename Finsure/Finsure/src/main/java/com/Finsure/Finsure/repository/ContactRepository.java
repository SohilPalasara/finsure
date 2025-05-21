package com.Finsure.Finsure.repository;

import com.Finsure.Finsure.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findByUser_UserId(Long userId);

}
