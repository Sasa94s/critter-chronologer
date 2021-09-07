package com.udacity.critter;

import com.google.common.collect.Sets;
import com.udacity.critter.controller.PetController;
import com.udacity.critter.controller.ScheduleController;
import com.udacity.critter.controller.UserController;
import com.udacity.critter.domain.dto.*;
import com.udacity.critter.domain.enums.EmployeeSkill;
import com.udacity.critter.utils.DTOUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is a set of functional tests to validate the basic capabilities desired for this application.
 * Students will need to configure the application to run these tests by adding application.properties file
 * to the test/resources directory that specifies the datasource. It can run using an in-memory H2 instance
 * and should not try to re-use the same datasource used by the rest of the app.
 *
 * These tests should all pass once the project is complete.
 */
@Transactional
@ContextConfiguration(classes = CritterApplication.class)
@SpringBootTest(classes = CritterApplication.class)
public class CritterFunctionalTest {

    @Autowired
    private UserController userController;

    @Autowired
    private PetController petController;

    @Autowired
    private ScheduleController scheduleController;

    @PersistenceContext
    private EntityManager em;

    private void flushAndClear() {
        em.flush();
        em.clear();
    }

    @BeforeAll
    public static void setUp() {
    }

    @Test
    public void testCreateCustomer() {
        createCustomer();
    }

    @Test
    public void testCreateEmployee() {
        createEmployee();
    }

    @Test
    public void testAddPetsToCustomer() {
        CustomerDTO newCustomer = createCustomer();
        createPet(newCustomer);
    }

    @Test
    public void testFindPetsByOwner() {
        CustomerDTO newCustomer = createCustomer();
        createPets(2, newCustomer);
    }

    @Test
    public void testFindOwnerByPet() {
        CustomerDTO newCustomer = createCustomer();
        PetDTO newPet = createPet(newCustomer);
        flushAndClear();

        CustomerDTO owner = userController.getOwnerByPet(newPet.getId());

        Assertions.assertEquals(owner.getId(), newCustomer.getId());
        Assertions.assertEquals(owner.getPetIds().get(0), newPet.getId());
    }

    @Test
    public void testChangeEmployeeAvailability() {
        EmployeeDTO employeeDTO = DTOUtil.generateEmployee(null);
        Assertions.assertNotNull(employeeDTO.getDaysAvailable());

        final Set<DayOfWeek> daysAvailable = employeeDTO.getDaysAvailable();
        employeeDTO.setDaysAvailable(null);
        EmployeeDTO newEmployee = userController.saveEmployee(employeeDTO);
        Assertions.assertNull(newEmployee.getDaysAvailable());

        userController.setAvailability(daysAvailable, newEmployee.getId());
        EmployeeDTO retrievedEmployee = userController.getEmployee(newEmployee.getId());
        Assertions.assertEquals(daysAvailable, retrievedEmployee.getDaysAvailable());
    }

    @Test
    public void testFindEmployeesByServiceAndTime() {
        List<EmployeeDTO> employeeDTOs = DTOUtil.batchEmployee(null, null, null);
        List<EmployeeDTO> newEmployees = employeeDTOs.stream()
                .map(employeeDTO -> userController.saveEmployee(employeeDTO))
                .collect(Collectors.toList());

        //make a request that matches employee 1 or 2
        EmployeeRequestDTO eRequest1 = DTOUtil.generateEmployeeRequestDTO(newEmployees.get(0));
        EmployeeRequestDTO eRequest2 = DTOUtil.generateEmployeeRequestDTO(newEmployees.get(1));

        Set<Long> eIds1 = userController.findEmployeesForService(eRequest1).stream()
                .map(EmployeeDTO::getId)
                .collect(Collectors.toSet());

        List<EmployeeDTO> emp1Expected = newEmployees.stream()
                .filter(employeeDTO -> employeeDTO.getDaysAvailable().contains(eRequest1.getDate().getDayOfWeek()))
                .filter(employeeDTO -> employeeDTO.getSkills().contains(eRequest1.getSkills().iterator().next()))
                .collect(Collectors.toList());
        Set<Long> eIds1Expected = emp1Expected.stream()
                .map(UserDTO::getId)
                .collect(Collectors.toSet());

        Assertions.assertEquals(eIds1, eIds1Expected);

        //make a request that matches only employee 3
        Set<Long> eIds2 = userController.findEmployeesForService(eRequest2).stream()
                .map(EmployeeDTO::getId)
                .collect(Collectors.toSet());

        List<EmployeeDTO> emp2Expected = newEmployees.stream()
                .filter(employeeDTO -> employeeDTO.getDaysAvailable().contains(eRequest2.getDate().getDayOfWeek()))
                .filter(employeeDTO -> employeeDTO.getSkills().contains(eRequest2.getSkills().iterator().next()))
                .collect(Collectors.toList());
        Set<Long> eIds2Expected = emp2Expected.stream()
                .map(UserDTO::getId)
                .collect(Collectors.toSet());

        Assertions.assertEquals(eIds2, eIds2Expected);
    }

    @Test
    public void testSchedulePetsForServiceWithEmployee() {
        EmployeeDTO employeeDTO = DTOUtil.generateEmployee(null);
        userController.saveEmployee(employeeDTO);

        CustomerDTO customerDTO = DTOUtil.generateCustomer(null);
        CustomerDTO newCustomer = userController.saveCustomer(customerDTO);

        PetDTO petDTO = DTOUtil.generatePet(null, newCustomer.getId());
        petController.savePet(petDTO);
        ScheduleDTO scheduleDTO = DTOUtil.generateSchedule(null,
                Collections.singleton(EmployeeSkill.FEEDING),
                Collections.singletonList(petDTO.getId()),
                Collections.singletonList(employeeDTO.getId()),
                Collections.singletonList(customerDTO.getId()));
        scheduleController.createSchedule(scheduleDTO);
        ScheduleDTO retrievedSchedule = scheduleController.getAllSchedules().get(0);

        Assertions.assertEquals(scheduleDTO.getActivities(), retrievedSchedule.getActivities());
        Assertions.assertEquals(scheduleDTO.getDate(), retrievedSchedule.getDate());
        Assertions.assertEquals(scheduleDTO.getEmployeeIds(), retrievedSchedule.getEmployeeIds());
        Assertions.assertEquals(scheduleDTO.getPetIds(), retrievedSchedule.getPetIds());
    }

    private ScheduleDTO createSchedule(int customerCount, int employeeCount, int petCount) {
        List<CustomerDTO> customers = DTOUtil.batchCustomers(customerCount).stream()
                .map(customer -> userController.saveCustomer(customer))
                .collect(Collectors.toList());
        List<EmployeeDTO> employees = DTOUtil.batchEmployees(employeeCount).stream()
                .map(employee -> userController.saveEmployee(employee))
                .collect(Collectors.toList());
        List<PetDTO> pets = DTOUtil.batchPets(petCount, customers).stream()
                .map(pet -> petController.savePet(pet))
                .collect(Collectors.toList());

        return scheduleController.createSchedule(DTOUtil.generateSchedule(
                null,
                pets.stream().map(PetDTO::getId).distinct().collect(Collectors.toList()),
                employees.stream().map(UserDTO::getId).distinct().collect(Collectors.toList()),
                customers.stream().map(UserDTO::getId).distinct().collect(Collectors.toList())));
    }

    @Test
    public void testFindScheduleByEntities() {
        ScheduleDTO schedule1 = createSchedule(1, 1, 2);
        ScheduleDTO schedule2 = createSchedule(1, 3, 1);
        //add a third schedule that shares some employees and pets with the other schedules
        ScheduleDTO schedule3 = scheduleController.createSchedule(DTOUtil.generateSchedule(null,
                Sets.newHashSet(EmployeeSkill.SHAVING, EmployeeSkill.PETTING),
                schedule2.getPetIds(),
                schedule1.getEmployeeIds(),
                schedule2.getCustomerIds()));

        /*
            We now have 3 schedule entries. The third schedule entry has the same employees as the 1st schedule
            and the same pets/owners as the second schedule. So if we look up schedule entries for the employee from
            schedule 1, we should get both the first and third schedule as our result.
         */

        // Employee 1 in is both schedule 1 and 3
        List<ScheduleDTO> schedules1 = scheduleController.getScheduleForEmployee(schedule1.getEmployeeIds().get(0));
        compareSchedules(schedule1, schedules1.get(0));
        compareSchedules(schedule3, schedules1.get(1));

        // Employee 2 is only in Schedule 2
        List<ScheduleDTO> schedules2 = scheduleController.getScheduleForEmployee(schedule2.getEmployeeIds().get(0));
        compareSchedules(schedule2, schedules2.get(0));

        // Pet 1 is only in Schedule 1
        List<ScheduleDTO> schedulesPet1 = scheduleController.getScheduleForPet(schedule1.getPetIds().get(0));
        compareSchedules(schedule1, schedulesPet1.get(0));

        // Pet from Schedule 2 is in both Schedules 2 and 3
        List<ScheduleDTO> schedulesPet2 = scheduleController.getScheduleForPet(schedule2.getPetIds().get(0));
        compareSchedules(schedule2, schedulesPet2.get(0));
        compareSchedules(schedule3, schedulesPet2.get(1));

        // Owner of the first Pet will only be in Schedule 1
        List<ScheduleDTO> scheduleCustomer1 = scheduleController.getScheduleForCustomer(
                userController.getOwnerByPet(schedule1.getPetIds().get(0)).getId());
        compareSchedules(schedule1, scheduleCustomer1.get(0));

        // Owner of Pet from Schedule 2 will be in both Schedules 2 and 3
        List<ScheduleDTO> scheduleCustomer2 = scheduleController.getScheduleForCustomer(
                userController.getOwnerByPet(schedule2.getPetIds().get(0)).getId());
        compareSchedules(schedule2, scheduleCustomer2.get(0));
        compareSchedules(schedule3, scheduleCustomer2.get(1));
    }

    private static void compareSchedules(ScheduleDTO schedule1, ScheduleDTO schedule2) {
        Assertions.assertEquals(
                schedule1.getPetIds().stream().sorted().collect(Collectors.toList()),
                schedule2.getPetIds().stream().sorted().collect(Collectors.toList()));
        Assertions.assertEquals(
                schedule1.getEmployeeIds().stream().sorted().collect(Collectors.toList()),
                schedule2.getEmployeeIds().stream().sorted().collect(Collectors.toList()));
        Assertions.assertEquals(
                schedule1.getCustomerIds().stream().sorted().collect(Collectors.toList()),
                schedule2.getCustomerIds().stream().sorted().collect(Collectors.toList()));
        Assertions.assertEquals(
                schedule1.getActivities().stream().sorted().collect(Collectors.toList()),
                schedule2.getActivities().stream().sorted().collect(Collectors.toList()));
        Assertions.assertEquals(schedule1.getDate(), schedule2.getDate());
    }


    private CustomerDTO createCustomer() {
        CustomerDTO generatedCustomer = DTOUtil.generateCustomer(null);
        CustomerDTO newCustomer = userController.saveCustomer(generatedCustomer);
        CustomerDTO retrievedCustomer = userController.getAllCustomers().get(0);
        Assertions.assertEquals(newCustomer.getName(), generatedCustomer.getName());
        Assertions.assertEquals(newCustomer.getId(), retrievedCustomer.getId());
        Assertions.assertTrue(retrievedCustomer.getId() > 0);

        return retrievedCustomer;
    }

    private EmployeeDTO createEmployee() {
        EmployeeDTO generatedEmployee = DTOUtil.generateEmployee(null);
        EmployeeDTO newEmployee = userController.saveEmployee(generatedEmployee);
        EmployeeDTO retrievedEmployee = userController.getEmployee(newEmployee.getId());
        Assertions.assertEquals(generatedEmployee.getSkills(), newEmployee.getSkills());
        Assertions.assertEquals(newEmployee.getId(), retrievedEmployee.getId());
        Assertions.assertEquals(retrievedEmployee.getId(), generatedEmployee.getId());

        return retrievedEmployee;
    }

    private PetDTO createPet(CustomerDTO customerDTO) {
        PetDTO petDTO = DTOUtil.generatePet(null, customerDTO.getId());
        PetDTO newPet = petController.savePet(petDTO);

        //make sure pet contains customer id
        PetDTO retrievedPet = petController.getPet(newPet.getId());
        Assertions.assertEquals(retrievedPet.getId(), newPet.getId());
        Assertions.assertEquals(retrievedPet.getOwnerId(), customerDTO.getId());

        //make sure you can retrieve pets by owner
        retrievedPet = petController.getPetsByOwner(customerDTO.getId()).get(0);
        Assertions.assertEquals(newPet.getId(), retrievedPet.getId());
        Assertions.assertEquals(newPet.getName(), retrievedPet.getName());

        flushAndClear();
        customerDTO = userController.getAllCustomers().get(0);

        //check to make sure customer now also contains pet
        Assertions.assertNotNull(customerDTO.getPetIds());
        Assertions.assertTrue(customerDTO.getPetIds().size() > 0);
        Assertions.assertEquals(customerDTO.getPetIds().get(0), retrievedPet.getId());

        return retrievedPet;
    }

    private void createPets(int count, CustomerDTO customerDTO) {
        List<PetDTO> petDTOs = DTOUtil.batchPet(
                IntStream.range(0, count)
                        .mapToObj(i -> new ImmutablePair<>((Long) null, customerDTO.getId()))
                        .toArray(Pair[]::new));

        List<PetDTO> newPets = petDTOs.stream()
                .map(petDTO -> petController.savePet(petDTO))
                .collect(Collectors.toList());
        List<PetDTO> retrievedPets = petController.getPetsByOwner(customerDTO.getId());

        Assertions.assertEquals(retrievedPets.size(), count);
        for (int i = 0; i < retrievedPets.size(); i++) {
            Assertions.assertEquals(retrievedPets.get(i).getOwnerId(), customerDTO.getId());
            Assertions.assertEquals(retrievedPets.get(i).getId(), newPets.get(i).getId());
        }
    }

}
