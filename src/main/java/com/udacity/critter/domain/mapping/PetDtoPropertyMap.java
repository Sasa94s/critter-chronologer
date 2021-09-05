package com.udacity.critter.domain.mapping;

import com.udacity.critter.domain.dto.PetDTO;
import com.udacity.critter.domain.entity.Pet;
import com.udacity.critter.domain.mapping.converter.CustomerIdsConverter;
import com.udacity.critter.repository.CustomerRepository;
import org.modelmapper.PropertyMap;

public class PetDtoPropertyMap extends PropertyMap<PetDTO, Pet> {
    private final CustomerRepository customerRepository;

    public PetDtoPropertyMap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    protected void configure() {
        using(new CustomerIdsConverter(customerRepository)).map(source.getOwnerId(), destination.getOwner());
    }
}
