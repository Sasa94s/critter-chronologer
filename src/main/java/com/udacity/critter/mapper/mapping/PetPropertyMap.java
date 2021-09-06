package com.udacity.critter.mapper.mapping;

import com.udacity.critter.domain.dto.PetDTO;
import com.udacity.critter.domain.entity.Pet;
import com.udacity.critter.mapper.converter.CustomersConverter;
import com.udacity.critter.mapper.converter.PetTypeConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetPropertyMap extends PropertyMap<Pet, PetDTO> {
    private final CustomersConverter customersConverter;
    private final PetTypeConverter petTypeConverter;

    @Override
    protected void configure() {
        using(customersConverter).map(source.getOwner(), destination.getOwnerId());
        using(petTypeConverter).map(source.getType(), destination.getType());
    }
}
