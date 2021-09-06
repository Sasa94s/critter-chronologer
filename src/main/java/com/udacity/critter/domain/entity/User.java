package com.udacity.critter.domain.entity;

import com.udacity.critter.domain.contract.BaseIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@DiscriminatorValue("User")
public class User implements BaseIdEntity<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String city;
    private String fullAddress;

}
