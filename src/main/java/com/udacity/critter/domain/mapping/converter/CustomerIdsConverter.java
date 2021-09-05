package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Customer;
import com.udacity.critter.repository.CustomerRepository;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerIdsConverter extends AbstractConverter<List<Long>, List<Customer>> {
    private final CustomerRepository repository;

    public CustomerIdsConverter(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    protected List<Customer> convert(List<Long> customerIds) {
        return customerIds.stream()
                .map(repository::getById)
                .collect(Collectors.toList());
    }
}
