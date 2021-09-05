package com.udacity.critter.repository;

import com.udacity.critter.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    long countById(Long id);

    @Query("SELECT COUNT(e) FROM Event e WHERE e.name = :name AND e.date = :date")
    long countByNameDate(String name, LocalDate date);

    long countByName(String name);

    @Query("SELECT COUNT(e) FROM Event e " +
            "JOIN e.employees emp " +
            "WHERE e.date = :date AND " +
            "(e.start <= :start AND e.end >= :start) OR " +
            "(e.start <= :end AND e.end >= :end) AND " +
            "emp.id IN :employeeIds")
    long countBySlotAndEmployeeId(
            @Param("date") LocalDate date,
            @Param("start") LocalTime start,
            @Param("end") LocalTime end,
            @Param("employeeIds") List<Long> employeeIds
    );

    @Query("SELECT COUNT(e) FROM Event e " +
            "JOIN e.customers c " +
            "WHERE e.date = :date AND " +
            "(e.start <= :start AND e.end >= :start) OR " +
            "(e.start <= :end AND e.end >= :end) AND " +
            "c.id IN :customerIds")
    long countBySlotAndCustomerId(
            @Param("date") LocalDate date,
            @Param("start") LocalTime start,
            @Param("end") LocalTime end,
            @Param("customerIds") List<Long> customerIds
    );

    @Query("SELECT COUNT(e) FROM Event e " +
            "JOIN e.pets p " +
            "WHERE e.date = :date AND " +
            "(e.start <= :start AND e.end >= :start) OR " +
            "(e.start <= :end AND e.end >= :end) AND " +
            "p.id IN :petIds")
    long countBySlotAndPetId(
            @Param("date") LocalDate date,
            @Param("start") LocalTime start,
            @Param("end") LocalTime end,
            @Param("petIds") List<Long> petIds
    );

    @Query("SELECT e FROM Event e " +
            "JOIN e.customers c " +
            "WHERE c.id = :customerId")
    List<Event> getAllByCustomerId(Long customerId);

    @Query("SELECT e FROM Event e " +
            "JOIN e.employees emp " +
            "WHERE emp.id = :employeeId")
    List<Event> getAllByEmployeeId(Long employeeId);

    @Query("SELECT e FROM Event e " +
            "JOIN e.pets p " +
            "WHERE p.id = :petId")
    List<Event> getAllByPetId(Long petId);

}
