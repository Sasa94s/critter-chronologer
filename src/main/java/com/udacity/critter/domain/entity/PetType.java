package com.udacity.critter.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class PetType {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String type;
    private String classification;
}
