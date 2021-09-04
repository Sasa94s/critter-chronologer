package com.udacity.critter.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Activity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Column(columnDefinition = "yes_no")
    private boolean inHouse;

    @ManyToOne
    private Event event;

}
