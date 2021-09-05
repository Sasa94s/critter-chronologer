package com.udacity.critter.repository;

import com.udacity.critter.domain.entity.Employee;
import com.udacity.critter.domain.enums.EmployeeSkill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends UserRepository<Employee> {
    @Query("SELECT COUNT(e) FROM Employee e " +
            "JOIN e.skills s " +
            "WHERE s = :skill")
    long countBySkill(EmployeeSkill skill);

    @Query("SELECT e FROM Employee e " +
            "JOIN e.skills s " +
            "JOIN e.daysAvailable d " +
            "WHERE s IN :skills AND " +
            "d = :dayOfWeek")
    List<Employee> getAllBySkillsAvailability(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek);
}
