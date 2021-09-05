package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Employee;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeesConverter extends AbstractConverter<List<Employee>, List<Long>> {
    @Override
    protected List<Long> convert(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }
}
