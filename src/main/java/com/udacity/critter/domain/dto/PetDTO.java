package com.udacity.critter.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
public class PetDTO {
    private long id;
    private String type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;
}
