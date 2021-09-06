package com.udacity.critter.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "Customer")
public class Customer extends User {

    private String notes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Set<Pet> pets;

    @ManyToOne
    private Event event;
}
