package com.udacity.critter.domain.mapping;

import com.udacity.critter.domain.dto.ScheduleDTO;
import com.udacity.critter.domain.entity.Event;
import com.udacity.critter.domain.mapping.converter.CustomerIdsConverter;
import com.udacity.critter.domain.mapping.converter.EmployeeIdsConverter;
import com.udacity.critter.domain.mapping.converter.PetIdsConverter;
import com.udacity.critter.domain.mapping.converter.StringTimeConverter;
import com.udacity.critter.repository.CustomerRepository;
import com.udacity.critter.repository.EmployeeRepository;
import com.udacity.critter.repository.PetRepository;
import org.modelmapper.PropertyMap;

public class ScheduleDtoPropertyMap extends PropertyMap<ScheduleDTO, Event> {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;

    public ScheduleDtoPropertyMap(
            CustomerRepository customerRepository,
            EmployeeRepository employeeRepository,
            PetRepository petRepository
    ) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    @Override
    protected void configure() {
        using(new StringTimeConverter()).map(source.getStart(), destination.getStart());
        using(new StringTimeConverter()).map(source.getEnd(), destination.getEnd());
        using(new CustomerIdsConverter(customerRepository)).map(source.getCustomerIds(), destination.getCustomers());
        using(new EmployeeIdsConverter(employeeRepository)).map(source.getEmployeeIds(), destination.getEmployees());
        using(new PetIdsConverter(petRepository)).map(source.getPetIds(), destination.getPets());
    }
}
