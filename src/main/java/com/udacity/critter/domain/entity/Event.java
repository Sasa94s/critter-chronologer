package com.udacity.critter.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private LocalDate date;
    private OffsetTime start;
    private OffsetTime end;

    @OneToMany(mappedBy = "event")
    private Set<Activity> activities;

    @OneToMany(mappedBy = "event")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "event")
    private Set<Customer> customers;

    @OneToMany(mappedBy = "event")
    private Set<Pet> pets;

}
