package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Employee;
import com.udacity.critter.repository.EmployeeRepository;
import org.modelmapper.AbstractConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeIdsConverter extends AbstractConverter<List<Long>, Set<Employee>> {
    private final EmployeeRepository repository;

    public EmployeeIdsConverter(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Set<Employee> convert(List<Long> employeeIds) {
        if (employeeIds == null || employeeIds.isEmpty()) return new HashSet<>();

        return employeeIds.stream()
                .map(repository::getById)
                .collect(Collectors.toSet());
    }
}
