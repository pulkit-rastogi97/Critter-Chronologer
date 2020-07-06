package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.exception.ScheduleNotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Value("${not.found.schedule}")
    String scheduleError;

    @Value("${not.found.pet}")
    String petError;

    @Value("${not.found.employee}")
    String employeeError;

    @Value("${not.found.customer}")
    String customerError;

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByPetId(Long petId) {
        if(petId == null || petId <= 0)
            throw  new PetNotFoundException(petError);
        Optional<Pet> pet = petRepository.findById(petId);
        if(pet.isPresent()) {
            return scheduleRepository.findAllByPetsContains(pet.get());
        }
        throw new ScheduleNotFoundException(scheduleError);
    }

    public List<Schedule> getSchedulesByEmployeeId(Long employeeId) {
        if(employeeId == null || employeeId <= 0)
            throw  new EmployeeNotFoundException(employeeError);
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()) {
            return scheduleRepository.findAllByEmployeesContains(employee.get());
        }
        throw new ScheduleNotFoundException(scheduleError);
    }

    public List<Schedule> getSchedulesByCustomerId(Long customerId) {
        if(customerId == null || customerId <= 0)
            throw  new CustomerNotFoundException(customerError);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()) {
            return scheduleRepository.findAllByPetsIn(customer.get().getPets());
        }
        throw new ScheduleNotFoundException(scheduleError);
    }

    public Schedule createSchedule(Schedule schedule, List<Long> petIds, List<Long> employeeIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        List<Pet> pets = petRepository.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.saveAndFlush(schedule);
    }
}
