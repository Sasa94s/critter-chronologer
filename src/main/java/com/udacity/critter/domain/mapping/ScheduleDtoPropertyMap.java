package com.udacity.critter.domain.mapping;

import com.udacity.critter.domain.dto.ScheduleDTO;
import com.udacity.critter.domain.entity.Event;
import com.udacity.critter.domain.mapping.converter.CustomerIdsConverter;
import com.udacity.critter.domain.mapping.converter.EmployeeIdsConverter;
import com.udacity.critter.domain.mapping.converter.PetIdsConverter;
import com.udacity.critter.domain.mapping.converter.StringTimeConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleDtoPropertyMap extends PropertyMap<ScheduleDTO, Event> {
    private final StringTimeConverter stringTimeConverter;
    private final CustomerIdsConverter customerIdsConverter;
    private final EmployeeIdsConverter employeeIdsConverter;
    private final PetIdsConverter petIdsConverter;

    @Override
    protected void configure() {
        using(stringTimeConverter).map(source.getStart(), destination.getStart());
        using(stringTimeConverter).map(source.getEnd(), destination.getEnd());
        using(customerIdsConverter).map(source.getCustomerIds(), destination.getCustomers());
        using(employeeIdsConverter).map(source.getEmployeeIds(), destination.getEmployees());
        using(petIdsConverter).map(source.getPetIds(), destination.getPets());
    }
}
