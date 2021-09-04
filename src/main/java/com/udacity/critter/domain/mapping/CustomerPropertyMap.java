package com.udacity.critter.domain.mapping;

import com.udacity.critter.domain.dto.CustomerDTO;
import com.udacity.critter.domain.entity.Customer;
import com.udacity.critter.domain.mapping.converter.PetsConverter;
import org.modelmapper.PropertyMap;

public class CustomerPropertyMap extends PropertyMap<Customer, CustomerDTO> {
    @Override
    protected void configure() {
        using(new PetsConverter()).map(source.getPets(), destination.getPetIds());
    }
}
