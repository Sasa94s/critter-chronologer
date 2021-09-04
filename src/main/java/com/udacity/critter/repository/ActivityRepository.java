package com.udacity.critter.repository;

import com.udacity.critter.domain.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    long countByName(String name);

}
