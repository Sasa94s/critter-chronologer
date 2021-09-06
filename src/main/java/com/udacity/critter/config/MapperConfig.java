package com.udacity.critter.config;

import com.udacity.critter.domain.mapping.*;
import com.udacity.critter.domain.mapping.converter.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MapperConfig {

    private final CustomerPropertyMap customerPropertyMap;
    private final CustomerDtoPropertyMap customerDtoPropertyMap;
    private final PetPropertyMap petPropertyMap;
    private final PetDtoPropertyMap petDtoPropertyMap;
    private final SchedulePropertyMap schedulePropertyMap;
    private final ScheduleDtoPropertyMap scheduleDtoPropertyMap;

    private final CustomerIdConverter customerIdConverter;
    private final CustomerIdsConverter customerIdsConverter;
    private final EmployeeIdsConverter employeeIdsConverter;
    private final EmployeesConverter employeesConverter;
    private final PetIdsConverter petIdsConverter;
    private final PetsConverter petsConverter;
    private final PetTypeConverter petTypeConverter;
    private final StringPetTypeConverter stringPetTypeConverter;
    private final StringTimeConverter stringTimeConverter;
    private final TimeStringConverter timeStringConverter;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.addConverter(customerIdConverter);
        mapper.addConverter(customerIdsConverter);
        mapper.addConverter(employeeIdsConverter);
        mapper.addConverter(employeesConverter);
        mapper.addConverter(petIdsConverter);
        mapper.addConverter(petsConverter);
        mapper.addConverter(petTypeConverter);
        mapper.addConverter(stringPetTypeConverter);
        mapper.addConverter(stringTimeConverter);
        mapper.addConverter(timeStringConverter);

        mapper.addMappings(customerPropertyMap);
        mapper.addMappings(customerDtoPropertyMap);
        mapper.addMappings(petPropertyMap);
        mapper.addMappings(petDtoPropertyMap);
        mapper.addMappings(schedulePropertyMap);
        mapper.addMappings(scheduleDtoPropertyMap);
        return mapper;
    }
}
