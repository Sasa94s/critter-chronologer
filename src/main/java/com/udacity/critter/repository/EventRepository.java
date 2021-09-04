package com.udacity.critter.repository;

import com.udacity.critter.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.OffsetTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    long countByName(String name);

    @Query("SELECT COUNT(e) FROM Event e " +
            "JOIN e.employees emp " +
            "WHERE e.date = :date AND " +
            "e.start = :start AND " +
            "e.end = :end AND " +
            "emp.id = :employeeId")
    long countBySlotAndEmployeeId(
            @Param("date") LocalDate date,
            @Param("start") OffsetTime start,
            @Param("end") OffsetTime end,
            @Param("employeeId") Long employeeId
    );

    @Query("SELECT COUNT(e) FROM Event e " +
            "JOIN e.customers c " +
            "WHERE e.date = :date AND " +
            "e.start = :start AND " +
            "e.end = :end AND " +
            "c.id = :customerId")
    long countBySlotAndCustomerId(
            @Param("date") LocalDate date,
            @Param("start") OffsetTime start,
            @Param("end") OffsetTime end,
            @Param("customerId") Long customerId
    );

    @Query("SELECT COUNT(e) FROM Event e " +
            "JOIN e.pets p " +
            "WHERE e.date = :date AND " +
            "e.start = :start AND " +
            "e.end = :end AND " +
            "p.id = :petId")
    long countBySlotAndPetId(
            @Param("date") LocalDate date,
            @Param("start") OffsetTime start,
            @Param("end") OffsetTime end,
            @Param("petId") Long petId
    );
}
