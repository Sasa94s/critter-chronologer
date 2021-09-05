package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Pet;
import com.udacity.critter.repository.PetRepository;
import org.modelmapper.AbstractConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PetIdsConverter extends AbstractConverter<List<Long>, Set<Pet>> {
    private final PetRepository repository;

    public PetIdsConverter(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Set<Pet> convert(List<Long> petIds) {
        if (petIds == null || petIds.isEmpty()) return new HashSet<>();

        return petIds.stream()
                .map(repository::getById)
                .collect(Collectors.toSet());
    }
}
