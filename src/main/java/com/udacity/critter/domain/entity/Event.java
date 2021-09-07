package com.udacity.critter.domain.entity;

import com.udacity.critter.domain.contract.BaseIdEntity;
import com.udacity.critter.domain.enums.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Event implements BaseIdEntity<Long> {
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

    @ManyToMany(cascade = CascadeType.ALL)
    @Where(clause = "DTYPE='Employee'")
    private Set<Employee> employees;

    @ManyToMany(cascade = CascadeType.ALL)
    @Where(clause = "DTYPE='Customer'")
    private Set<Customer> customers;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Pet> pets;

}
