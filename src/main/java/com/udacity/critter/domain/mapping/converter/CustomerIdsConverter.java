package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Customer;
import com.udacity.critter.repository.CustomerRepository;
import org.modelmapper.AbstractConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerIdsConverter extends AbstractConverter<List<Long>, Set<Customer>> {
    private final CustomerRepository repository;

    public CustomerIdsConverter(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Set<Customer> convert(List<Long> customerIds) {
        if (customerIds == null || customerIds.isEmpty()) return new HashSet<>();

        return customerIds.stream()
                .map(repository::getById)
                .collect(Collectors.toSet());
    }
}
