package com.udacity.critter.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
public class CustomerDTO extends UserDTO {
    private String notes;
    private List<Long> petIds;
}
