package com.udacity.critter.mapper;

import com.udacity.critter.mapper.converter.*;
import com.udacity.critter.mapper.mapping.*;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MapperRepository {

    @Bean
    public List<Converter<?, ?>> converters(
            AbstractEntitiesConverter<Long> abstractEntitiesConverter,
            CustomerIdConverter customerIdConverter,
            CustomerIdsConverter customerIdsConverter,
            EmployeeIdsConverter employeeIdsConverter,
            PetIdsConverter petIdsConverter,
            StringPetTypeConverter stringPetTypeConverter,
            StringTimeConverter stringTimeConverter,
            TimeStringConverter timeStringConverter
    ) {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(abstractEntitiesConverter);
        converters.add(customerIdConverter);
        converters.add(customerIdsConverter);
        converters.add(employeeIdsConverter);
        converters.add(petIdsConverter);
        converters.add(stringPetTypeConverter);
        converters.add(stringTimeConverter);
        converters.add(timeStringConverter);

        return converters;
    }

    @Bean
    public List<PropertyMap<?, ?>> mappings(
            CustomerPropertyMap customerPropertyMap,
            CustomerDtoPropertyMap customerDtoPropertyMap,
            PetPropertyMap petPropertyMap,
            PetDtoPropertyMap petDtoPropertyMap,
            SchedulePropertyMap schedulePropertyMap,
            ScheduleDtoPropertyMap scheduleDtoPropertyMap
    ) {
        List<PropertyMap<?, ?>> mappings = new ArrayList<>();
        mappings.add(customerPropertyMap);
        mappings.add(customerDtoPropertyMap);
        mappings.add(petPropertyMap);
        mappings.add(petDtoPropertyMap);
        mappings.add(schedulePropertyMap);
        mappings.add(scheduleDtoPropertyMap);

        return mappings;
    }
}
