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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Pet> pets;

    @ManyToMany(mappedBy = "customers")
    private Set<Event> event;
}
