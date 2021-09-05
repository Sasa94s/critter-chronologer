package com.udacity.critter.service;

import com.udacity.critter.domain.dto.ScheduleDTO;
import com.udacity.critter.domain.entity.Event;
import com.udacity.critter.domain.mapping.converter.StringTimeConverter;
import com.udacity.critter.exception.AlreadyExistsException;
import com.udacity.critter.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ModelMapper mapper;
    private final EventRepository repository;

    public ScheduleService(
            ModelMapper mapper,
            EventRepository repository
    ) {
        this.mapper = mapper;
        this.repository = repository;
    }

    private void validateForCreate(ScheduleDTO scheduleDTO) {
        long count = repository.countById(scheduleDTO.getId());
        if (count == 1) {
            throw new AlreadyExistsException(String.format("Schedule ID %d already exists", scheduleDTO.getId()));
        }
        count = repository.countByNameDate(scheduleDTO.getName(), scheduleDTO.getDate());
        if (count == 1) {
            throw new AlreadyExistsException("Schedule is already created before");
        }
        LocalTime start = StringTimeConverter.parse(scheduleDTO.getStart());
        LocalTime end = StringTimeConverter.parse(scheduleDTO.getEnd());
        count = repository.countBySlotAndCustomerId(
                scheduleDTO.getDate(),
                start,
                end,
                scheduleDTO.getCustomerIds()
        );
        if (count >= 1) {
            throw new AlreadyExistsException("One or more Customer(s) has a schedule conflict");
        }
        count = repository.countBySlotAndEmployeeId(
                scheduleDTO.getDate(),
                start,
                end,
                scheduleDTO.getCustomerIds()
        );
        if (count >= 1) {
            throw new AlreadyExistsException("One or more Employee(s) has a schedule conflict");
        }
        count = repository.countBySlotAndPetId(
                scheduleDTO.getDate(),
                start,
                end,
                scheduleDTO.getCustomerIds()
        );
        if (count >= 1) {
            throw new AlreadyExistsException("One or more Pet(s) has a schedule conflict");
        }
    }

    public ScheduleDTO create(ScheduleDTO scheduleDTO) {
        validateForCreate(scheduleDTO);
        Event event = mapper.map(scheduleDTO, Event.class);
        repository.save(event);

        return scheduleDTO;
    }

    public List<ScheduleDTO> getAll() {
        return repository.findAll().stream()
                .map(event -> mapper.map(event, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    public List<ScheduleDTO> getAllByCustomerId(long customerId) {
        return repository.getAllByCustomerId(customerId).stream()
                .map(event -> mapper.map(event, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    public List<ScheduleDTO> getAllByEmployeeId(long employeeId) {
        return repository.getAllByEmployeeId(employeeId).stream()
                .map(event -> mapper.map(event, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    public List<ScheduleDTO> getAllByPetId(long petId) {
        return repository.getAllByPetId(petId).stream()
                .map(event -> mapper.map(event, ScheduleDTO.class))
                .collect(Collectors.toList());
    }
}
