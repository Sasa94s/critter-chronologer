package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Customer;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersConverter extends AbstractConverter<List<Customer>, List<Long>> {
    @Override
    protected List<Long> convert(List<Customer> customers) {
        return customers.stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
    }
}
