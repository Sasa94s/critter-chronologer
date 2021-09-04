package com.udacity.critter.config;

import com.udacity.critter.domain.mapping.CustomerDtoPropertyMap;
import com.udacity.critter.domain.mapping.CustomerPropertyMap;
import com.udacity.critter.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    private final PetRepository petRepository;

    public MapperConfig(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new CustomerPropertyMap());
        mapper.addMappings(new CustomerDtoPropertyMap(petRepository));

        return mapper;
    }
}
