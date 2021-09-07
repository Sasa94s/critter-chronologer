package com.udacity.critter.domain.entity;

import com.udacity.critter.domain.enums.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "Employee")
public class Employee extends User {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Skill", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "skill")
    private Set<EmployeeSkill> skills;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "DayOfWeek", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(mappedBy = "employees")
    private Set<Event> event;

}
