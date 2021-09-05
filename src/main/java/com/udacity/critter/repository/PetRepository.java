package com.udacity.critter.repository;

import com.udacity.critter.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    long countById(Long id);

    @Query("SELECT COUNT(p) FROM Pet p " +
            "JOIN p.owner c " +
            "WHERE c.id = :customerId")
    long countByCustomerId(Long customerId);

    @Query("SELECT p FROM Pet p " +
            "JOIN p.owner o " +
            "WHERE o.id = :ownerId")
    List<Pet> getAllByOwnerId(Long ownerId);

    Optional<Pet> findByName(String name);
}
