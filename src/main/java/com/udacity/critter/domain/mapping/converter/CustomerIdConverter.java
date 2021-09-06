package com.udacity.critter.domain.mapping.converter;

import com.udacity.critter.domain.entity.Customer;
import com.udacity.critter.exception.NotFoundException;
import com.udacity.critter.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerIdConverter extends AbstractConverter<Long, Customer> {
    private final CustomerRepository repository;

    @Override
    protected Customer convert(Long customerId) {
        return repository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer does not exist"));
    }
}
