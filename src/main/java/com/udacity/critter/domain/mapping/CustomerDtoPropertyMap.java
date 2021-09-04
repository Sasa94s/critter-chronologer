package com.udacity.critter.domain.mapping;

import com.udacity.critter.domain.dto.CustomerDTO;
import com.udacity.critter.domain.entity.Customer;
import com.udacity.critter.domain.mapping.converter.PetIdsConverter;
import com.udacity.critter.repository.PetRepository;
import org.modelmapper.PropertyMap;

public class CustomerDtoPropertyMap extends PropertyMap<CustomerDTO, Customer> {
    private final PetRepository petRepository;

    public CustomerDtoPropertyMap(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    protected void configure() {
        using(new PetIdsConverter(petRepository)).map(source.getPetIds(), destination.getPets());
    }
}
