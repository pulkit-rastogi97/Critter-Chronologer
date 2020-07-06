package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TranslatorDTO {

    public PetDTO getPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet,petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    public List<Object> getPet(PetDTO petDTO) {
        List<Object> petDetails = new ArrayList<>();
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO,pet);
        petDetails.add(pet);
        petDetails.add(petDTO.getOwnerId());
        return petDetails;
    }

    public CustomerDTO getCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        customerDTO.setPetIds(customer.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        return customerDTO;
    }

    public List<Object> getCustomer(CustomerDTO customerDTO){
        List<Object> customerDetails = new ArrayList<>();
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        customerDetails.add(customer);
        customerDetails.add(customerDTO.getPetIds());
        return customerDetails;
    }

    public ScheduleDTO getScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule,scheduleDTO);
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream()
                .map(employee -> employee.getId()).collect(Collectors.toList()));
        scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        return scheduleDTO;
    }

    public List<Object> getSchedule(ScheduleDTO scheduleDTO)
    {
        Schedule schedule = new Schedule();
        List<Object> scheduleDetails = new ArrayList<>();
        BeanUtils.copyProperties(scheduleDTO,schedule);
        scheduleDetails.add(schedule);
        scheduleDetails.add(scheduleDTO.getPetIds());
        scheduleDetails.add(scheduleDTO.getEmployeeIds());
        return scheduleDetails;
    }

    public EmployeeDTO getEmployeeDTO(Employee employee)
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee,employeeDTO);
        return employeeDTO;
    }

    public Employee getEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        return employee;
    }

    public List<PetDTO> getPetDTOs(List<Pet> pets) {
        List<PetDTO> petDTOs = new ArrayList<>();
        PetDTO petDTO ;
        for(Pet pet : pets)
        {
            petDTO = new PetDTO();
            BeanUtils.copyProperties(pet,petDTO);
            petDTO.setOwnerId(pet.getOwner().getId());
            petDTOs.add(petDTO);
        }
        return petDTOs;
    }

    public List<CustomerDTO> getCustomerDTOs(List<Customer> customers) {

        List<CustomerDTO> customerDTOs = new ArrayList<>();
        CustomerDTO customerDTO;
        for(Customer customer: customers){
            customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customer,customerDTO);
            customerDTO.setPetIds(customer.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    public List<EmployeeDTO> getEmployeeDTOs(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        EmployeeDTO employeeDTO;
        for(Employee employee : employees)
        {
            employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employee,employeeDTO);
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    public List<ScheduleDTO> getScheduleDTOs(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        ScheduleDTO scheduleDTO;
        for(Schedule schedule : schedules){
            scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(schedule,scheduleDTO);
            scheduleDTO.setEmployeeIds(schedule.getEmployees().stream()
                    .map(employee -> employee.getId()).collect(Collectors.toList()));
            scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
            scheduleDTOs.add(scheduleDTO);
        }
        return  scheduleDTOs;
    }
}
