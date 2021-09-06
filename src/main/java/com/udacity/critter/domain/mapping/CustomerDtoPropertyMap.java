package com.udacity.critter.domain.mapping;

import com.udacity.critter.domain.dto.CustomerDTO;
import com.udacity.critter.domain.entity.Customer;
import com.udacity.critter.domain.mapping.converter.PetIdsConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerDtoPropertyMap extends PropertyMap<CustomerDTO, Customer> {
    private final PetIdsConverter petIdsConverter;

    @Override
    protected void configure() {
        using(petIdsConverter).map(source.getPetIds(), destination.getPets());
    }
}
