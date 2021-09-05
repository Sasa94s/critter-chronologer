package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Pet;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PetsConverter extends AbstractConverter<Set<Pet>, List<Long>> {
    @Override
    protected List<Long> convert(Set<Pet> pets) {
        return pets.stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
    }
}
