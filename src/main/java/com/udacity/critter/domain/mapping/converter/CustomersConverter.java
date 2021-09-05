package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Customer;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomersConverter extends AbstractConverter<Set<Customer>, List<Long>> {
    @Override
    protected List<Long> convert(Set<Customer> customers) {
        return customers.stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
    }
}
