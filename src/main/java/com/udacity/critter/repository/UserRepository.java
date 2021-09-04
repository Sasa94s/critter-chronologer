package com.udacity.critter.repository;

import com.udacity.critter.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    long countById(Long id);

    long countByPhoneNumber(String phoneNumber);

    long countByEmailAddress(String emailAddress);

    Optional<T> findByPhoneNumber(String phoneNumber);

    Optional<T> findByEmailAddress(String emailAddress);
}
