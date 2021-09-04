package com.udacity.critter.repository;

import com.udacity.critter.domain.entity.Employee;
import com.udacity.critter.domain.enums.EmployeeSkill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends UserRepository<Employee> {
    @Query("SELECT COUNT(e) FROM Employee e " +
            "JOIN e.skills s " +
            "WHERE s = :skill")
    long countBySkill(EmployeeSkill skill);
}
