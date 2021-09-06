package com.udacity.critter.domain.entity;

import com.udacity.critter.domain.contract.BaseIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PetType implements BaseIdEntity<Long> {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
}
