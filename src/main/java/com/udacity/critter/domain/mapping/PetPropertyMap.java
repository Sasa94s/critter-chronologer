package com.udacity.critter.domain.mapping;

import com.udacity.critter.domain.dto.PetDTO;
import com.udacity.critter.domain.entity.Pet;
import com.udacity.critter.domain.mapping.converter.CustomersConverter;
import org.modelmapper.PropertyMap;

public class PetPropertyMap extends PropertyMap<Pet, PetDTO> {
    @Override
    protected void configure() {
        using(new CustomersConverter()).map(source.getOwner(), destination.getOwnerId());
    }
}
