package com.udacity.critter.mapper.mapping;

import com.udacity.critter.domain.dto.CustomerDTO;
import com.udacity.critter.domain.entity.Customer;
import com.udacity.critter.mapper.converter.AbstractEntitiesConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerPropertyMap extends PropertyMap<Customer, CustomerDTO> {
    private final AbstractEntitiesConverter<Long> abstractEntitiesConverter;

    @Override
    protected void configure() {
        using(abstractEntitiesConverter).map(source.getPets(), destination.getPetIds());
    }
}
