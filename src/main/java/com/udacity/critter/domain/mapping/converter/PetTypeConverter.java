package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.PetType;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class PetTypeConverter extends AbstractConverter<PetType, String> {
    @Override
    protected String convert(PetType petType) {
        return petType.getType();
    }
}
