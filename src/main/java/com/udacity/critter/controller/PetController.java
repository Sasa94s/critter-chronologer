package com.udacity.critter.controller;

import com.udacity.critter.domain.dto.PetDTO;
import com.udacity.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return service.create(petDTO);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return service.get(petId);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return service.getAll();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return service.getAllByOwnerId(ownerId);
    }
}
