package com.udacity.critter.domain.entity;

import com.udacity.critter.domain.enums.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Activity", joinColumns = @JoinColumn(name = "event_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "activity")
    private Set<EmployeeSkill> activities;

    @OneToMany(mappedBy = "event")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "event")
    private Set<Customer> customers;

    @OneToMany(mappedBy = "event")
    private Set<Pet> pets;

}
