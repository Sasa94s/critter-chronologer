package com.udacity.critter.domain.mapping;

import com.udacity.critter.domain.dto.ScheduleDTO;
import com.udacity.critter.domain.entity.Event;
import com.udacity.critter.domain.mapping.converter.CustomersConverter;
import com.udacity.critter.domain.mapping.converter.EmployeesConverter;
import com.udacity.critter.domain.mapping.converter.PetsConverter;
import com.udacity.critter.domain.mapping.converter.TimeStringConverter;
import org.modelmapper.PropertyMap;

public class SchedulePropertyMap extends PropertyMap<Event, ScheduleDTO> {
    @Override
    protected void configure() {
        using(new TimeStringConverter()).map(source.getStart(), destination.getStart());
        using(new TimeStringConverter()).map(source.getEnd(), destination.getEnd());
        using(new CustomersConverter()).map(source.getCustomers(), destination.getCustomerIds());
        using(new EmployeesConverter()).map(source.getEmployees(), destination.getEmployeeIds());
        using(new PetsConverter()).map(source.getPets(), destination.getPetIds());
    }
}
