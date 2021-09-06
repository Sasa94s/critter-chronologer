package com.udacity.critter.mapper.converter;

import com.udacity.critter.domain.entity.PetType;
import com.udacity.critter.repository.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StringPetTypeConverter extends AbstractConverter<String, PetType> {
    private final PetTypeRepository repository;

    /**
     * Returns PetType retrieved from DB, if not found it creates a new one in DB to retrieve
     *
     * @param type Pet type name
     * @return @PetType object representation of PetType
     */
    @Override
    protected PetType convert(String type) {
        Optional<PetType> optionalPetType = repository.findByType(type);
        if (!optionalPetType.isPresent()) {
            PetType petType = new PetType();
            petType.setType(type);
            repository.save(petType);

            return petType;
        }

        return optionalPetType.get();
    }
}
