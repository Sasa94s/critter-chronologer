package com.udacity.critter.service;

import com.udacity.critter.domain.dto.PetDTO;
import com.udacity.critter.domain.entity.Pet;
import com.udacity.critter.exception.AlreadyExistsException;
import com.udacity.critter.exception.NotFoundException;
import com.udacity.critter.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {
    private final ModelMapper mapper;
    private final PetRepository repository;

    private void validateForCreate(PetDTO petDTO) {
        long count = repository.countById(petDTO.getId());
        if (count == 1) {
            throw new AlreadyExistsException(String.format("ID %d already exists", petDTO.getId()));
        }
    }

    public PetDTO create(PetDTO petDTO) {
        validateForCreate(petDTO);
        Pet pet = mapper.map(petDTO, Pet.class);
        pet = repository.save(pet);
        petDTO.setId(pet.getId());

        return petDTO;
    }

    public PetDTO get(long id) {
        return repository.findById(id)
                .map(pet -> mapper.map(pet, PetDTO.class))
                .orElseThrow(() -> new NotFoundException(String.format("ID %d not found", id)));
    }

    public List<PetDTO> getAll() {
        return repository.findAll().stream()
                .map(pet -> mapper.map(pet, PetDTO.class))
                .collect(Collectors.toList());
    }

    public List<PetDTO> getAllByOwnerId(long ownerId) {
        return repository.getAllByOwnerId(ownerId).stream()
                .map(pet -> mapper.map(pet, PetDTO.class))
                .collect(Collectors.toList());
    }
}
