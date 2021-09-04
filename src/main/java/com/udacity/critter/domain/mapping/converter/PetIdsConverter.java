package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Pet;
import com.udacity.critter.repository.PetRepository;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

public class PetIdsConverter extends AbstractConverter<List<Long>, List<Pet>> {
    private final PetRepository repository;

    public PetIdsConverter(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    protected List<Pet> convert(List<Long> petIds) {
        return petIds.stream()
                .map(repository::getById)
                .collect(Collectors.toList());
    }
}
