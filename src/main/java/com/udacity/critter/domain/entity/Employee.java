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
public class Employee extends User {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "EmployeeSkill", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "DayOfWeek", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    @ManyToOne
    private Event event;

}
