package com.udacity.critter.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String city;
    private String fullAddress;

}
