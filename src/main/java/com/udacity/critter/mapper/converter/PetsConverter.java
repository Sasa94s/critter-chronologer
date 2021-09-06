package com.udacity.critter.mapper.converter;

import com.udacity.critter.domain.entity.Pet;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PetsConverter extends AbstractConverter<Set<Pet>, List<Long>> {
    @Override
    protected List<Long> convert(Set<Pet> pets) {
        return pets.stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
    }
}
