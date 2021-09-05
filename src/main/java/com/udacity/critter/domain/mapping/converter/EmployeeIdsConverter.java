package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Employee;
import com.udacity.critter.repository.EmployeeRepository;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeIdsConverter extends AbstractConverter<List<Long>, List<Employee>> {
    private final EmployeeRepository repository;

    public EmployeeIdsConverter(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected List<Employee> convert(List<Long> EmployeeIds) {
        return EmployeeIds.stream()
                .map(repository::getById)
                .collect(Collectors.toList());
    }
}
