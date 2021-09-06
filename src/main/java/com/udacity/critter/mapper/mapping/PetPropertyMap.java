package com.udacity.critter.mapper.mapping;

import com.udacity.critter.domain.dto.PetDTO;
import com.udacity.critter.domain.entity.Pet;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PetPropertyMap extends PropertyMap<Pet, PetDTO> {
    @Override
    protected void configure() {
        map(source.getOwner().getId(), destination.getOwnerId());
        map(source.getType().getType(), destination.getType());
    }
}
