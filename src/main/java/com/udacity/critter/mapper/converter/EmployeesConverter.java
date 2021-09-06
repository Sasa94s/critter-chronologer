package com.udacity.critter.mapper.converter;

import com.udacity.critter.domain.entity.Employee;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeesConverter extends AbstractConverter<Set<Employee>, List<Long>> {
    @Override
    protected List<Long> convert(Set<Employee> employees) {
        return employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }
}
