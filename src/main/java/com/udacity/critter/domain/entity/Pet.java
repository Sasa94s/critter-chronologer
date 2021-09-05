package com.udacity.critter.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Pet {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PetType type;

    private String name;

    @ManyToOne
    private Customer owner;

    private LocalDate birthDate;
    private String notes;

    @ManyToOne
    private Event event;

}
