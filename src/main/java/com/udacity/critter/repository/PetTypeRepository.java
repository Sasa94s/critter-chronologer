package com.udacity.critter.repository;

import com.udacity.critter.domain.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {

}
