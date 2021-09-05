package com.udacity.critter.service;

import com.udacity.critter.domain.dto.UserDTO;
import com.udacity.critter.domain.entity.User;
import com.udacity.critter.exception.AlreadyExistsException;
import com.udacity.critter.exception.EmptyException;
import com.udacity.critter.repository.UserRepository;

public abstract class UserService<T extends User, TDto extends UserDTO, TRepo extends UserRepository<T>> {
    protected TRepo repository;

    public UserService(TRepo repository) {
        this.repository = repository;
    }

    public boolean exists(long id) {
        return repository.countById(id) == 1;
    }

    public void validateForCreate(TDto t) {
        long count = repository.countById(t.getId());
        if (count == 1) {
            throw new AlreadyExistsException(String.format("ID %d already exists", t.getId()));
        }
        count = repository.countByPhoneNumber(t.getPhoneNumber());
        if (count == 1) {
            throw new AlreadyExistsException(String.format("Phone Number %s already exists", t.getPhoneNumber()));
        }
        count = repository.countByEmailAddress(t.getEmailAddress());
        if (count == 1) {
            throw new AlreadyExistsException(String.format("Email Address %s already exists", t.getEmailAddress()));
        }
        if (t.getCity().isEmpty()) {
            throw new EmptyException("City cannot be empty");
        }
    }
}
