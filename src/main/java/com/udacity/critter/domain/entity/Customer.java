package com.udacity.critter.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer extends User {

    private String notes;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

    @ManyToOne
    private Event event;
}
