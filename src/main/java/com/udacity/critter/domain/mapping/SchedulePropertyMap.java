package com.udacity.critter.domain.mapping;

import com.udacity.critter.domain.dto.ScheduleDTO;
import com.udacity.critter.domain.entity.Event;
import com.udacity.critter.domain.mapping.converter.CustomersConverter;
import com.udacity.critter.domain.mapping.converter.EmployeesConverter;
import com.udacity.critter.domain.mapping.converter.PetsConverter;
import com.udacity.critter.domain.mapping.converter.TimeStringConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulePropertyMap extends PropertyMap<Event, ScheduleDTO> {
    private final TimeStringConverter timeStringConverter;
    private final CustomersConverter customersConverter;
    private final EmployeesConverter employeesConverter;
    private final PetsConverter petsConverter;

    @Override
    protected void configure() {
        using(timeStringConverter).map(source.getStart(), destination.getStart());
        using(timeStringConverter).map(source.getEnd(), destination.getEnd());
        using(customersConverter).map(source.getCustomers(), destination.getCustomerIds());
        using(employeesConverter).map(source.getEmployees(), destination.getEmployeeIds());
        using(petsConverter).map(source.getPets(), destination.getPetIds());
    }
}
