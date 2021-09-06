package com.udacity.critter.repository;

import com.udacity.critter.domain.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
    Optional<PetType> findByType(String type);
}
