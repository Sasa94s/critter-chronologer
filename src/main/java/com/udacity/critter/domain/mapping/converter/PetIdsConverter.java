package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Pet;
import com.udacity.critter.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PetIdsConverter extends AbstractConverter<List<Long>, Set<Pet>> {
    private final PetRepository repository;

    @Override
    protected Set<Pet> convert(List<Long> petIds) {
        return repository.findAllByIds(petIds);
    }
}
