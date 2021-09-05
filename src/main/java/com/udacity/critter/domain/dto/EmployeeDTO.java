package com.udacity.critter.domain.dto;

import com.udacity.critter.domain.enums.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
public class EmployeeDTO extends UserDTO {
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;
}
