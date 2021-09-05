package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Employee;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeesConverter extends AbstractConverter<Set<Employee>, List<Long>> {
    @Override
    protected List<Long> convert(Set<Employee> employees) {
        return employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }
}
