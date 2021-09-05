package com.udacity.critter.service;

import com.udacity.critter.domain.dto.EmployeeDTO;
import com.udacity.critter.domain.dto.EmployeeRequestDTO;
import com.udacity.critter.domain.entity.Employee;
import com.udacity.critter.exception.NotFoundException;
import com.udacity.critter.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService extends UserService<Employee, EmployeeDTO, EmployeeRepository> {
    private final ModelMapper mapper;

    public EmployeeService(
            ModelMapper mapper,
            EmployeeRepository repository
    ) {
        super(repository);
        this.mapper = mapper;
    }

    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        validateForCreate(employeeDTO);
        Employee employee = mapper.map(employeeDTO, Employee.class);
        repository.save(employee);

        return employeeDTO;
    }

    public EmployeeDTO get(long id) {
        return repository.findById(id)
                .map(employee -> mapper.map(employee, EmployeeDTO.class))
                .orElseThrow(() -> new NotFoundException(String.format("ID %d not found", id)));
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long id) {
        if (!exists(id)) {
            throw new NotFoundException(String.format("Employee ID %d not found", id));
        }
        Optional<Employee> optionalEmployee = repository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setDaysAvailable(daysAvailable);
            repository.save(employee);
        }
    }

    public List<EmployeeDTO> getAllBySkillsAvailability(EmployeeRequestDTO employeeRequestDTO) {
        List<Employee> employees = repository.getAllBySkillsAvailability(
                employeeRequestDTO.getSkills(), employeeRequestDTO.getDate().getDayOfWeek());

        return employees.stream()
                .map(employee -> mapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
