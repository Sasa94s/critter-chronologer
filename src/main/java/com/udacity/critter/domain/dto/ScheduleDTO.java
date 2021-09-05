package com.udacity.critter.domain.dto;

import com.udacity.critter.domain.enums.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
public class ScheduleDTO {
    private long id;
    private String name;
    private List<Long> employeeIds;
    private List<Long> customerIds;
    private List<Long> petIds;
    private LocalDate date;
    private String start;
    private String end;
    private Set<EmployeeSkill> activities;
}
