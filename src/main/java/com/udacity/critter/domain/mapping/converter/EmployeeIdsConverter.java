package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Employee;
import com.udacity.critter.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class EmployeeIdsConverter extends AbstractConverter<List<Long>, Set<Employee>> {
    private final EmployeeRepository repository;

    @Override
    protected Set<Employee> convert(List<Long> employeeIds) {
        return repository.findAllByIds(employeeIds);
    }
}
