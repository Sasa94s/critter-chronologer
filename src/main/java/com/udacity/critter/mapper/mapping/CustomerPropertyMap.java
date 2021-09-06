package com.udacity.critter.mapper.mapping;

import com.udacity.critter.domain.dto.CustomerDTO;
import com.udacity.critter.domain.entity.Customer;
import com.udacity.critter.mapper.converter.PetsConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerPropertyMap extends PropertyMap<Customer, CustomerDTO> {
    private final PetsConverter petsConverter;

    @Override
    protected void configure() {
        using(petsConverter).map(source.getPets(), destination.getPetIds());
    }
}
