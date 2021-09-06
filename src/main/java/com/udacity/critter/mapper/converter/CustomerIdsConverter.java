package com.udacity.critter.mapper.converter;

import com.udacity.critter.domain.entity.Customer;
import com.udacity.critter.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomerIdsConverter extends AbstractConverter<List<Long>, Set<Customer>> {
    private final CustomerRepository repository;

    @Override
    protected Set<Customer> convert(List<Long> customerIds) {
        return repository.findAllByIds(customerIds);
    }
}
