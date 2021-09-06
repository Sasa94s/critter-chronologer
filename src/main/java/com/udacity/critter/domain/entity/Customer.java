package com.udacity.critter.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "Customer")
public class Customer extends User {

    private String notes;

    @OneToMany(mappedBy = "owner")
    private Set<Pet> pets;

    @ManyToOne
    private Event event;
}
