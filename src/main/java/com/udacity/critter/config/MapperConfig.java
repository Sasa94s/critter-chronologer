package com.udacity.critter.config;

import com.udacity.critter.domain.mapping.*;
import com.udacity.critter.repository.CustomerRepository;
import com.udacity.critter.repository.EmployeeRepository;
import com.udacity.critter.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;

    public MapperConfig(
            CustomerRepository customerRepository,
            EmployeeRepository employeeRepository,
            PetRepository petRepository
    ) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new CustomerPropertyMap());
        mapper.addMappings(new CustomerDtoPropertyMap(petRepository));
        mapper.addMappings(new PetPropertyMap());
        mapper.addMappings(new PetDtoPropertyMap(customerRepository));
        mapper.addMappings(new SchedulePropertyMap());
        mapper.addMappings(new ScheduleDtoPropertyMap(customerRepository, employeeRepository, petRepository));

        return mapper;
    }
}
