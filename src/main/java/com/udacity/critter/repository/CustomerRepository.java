package com.udacity.critter.repository;

import com.udacity.critter.domain.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends UserRepository<Customer> {
    @Query("SELECT COUNT(c) FROM Customer c " +
            "JOIN c.pets p " +
            "WHERE p.id = :petId")
    long countByPetId(Long petId);

    @Query("SELECT c FROM Customer c " +
            "JOIN c.pets p " +
            "WHERE p.id = :petId")
    Customer getByPetId(Long petId);
}
