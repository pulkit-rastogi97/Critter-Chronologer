package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Value("${not.found.employee}")
    String employeeError;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        if(employeeId == null || employeeId <= 0)
            throw new EmployeeNotFoundException(employeeError);
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()) {
            return employee.get();
        }
        throw new EmployeeNotFoundException(employeeError);
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable, Long employeeId) {
        if(employeeId == null || employeeId <= 0)
            throw new EmployeeNotFoundException(employeeError);
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()) {
             employee.get().setDaysAvailable(daysAvailable);
             employeeRepository.saveAndFlush(employee.get());
        }
    }

    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skillsRequired) {
        List<Employee> employeesAvailable = new ArrayList<>();
        List<Employee> employeeAvailableOnDays = employeeRepository.findAllByDaysAvailableContains(date.getDayOfWeek());
        int available;
        for(Employee employee : employeeAvailableOnDays) {
            available = 0;
            for (EmployeeSkill skill : skillsRequired) {
                if (employee.getSkills().contains(skill))
                    available++;
            }
            if(available == skillsRequired.size())
                employeesAvailable.add(employee);
        }
        return employeesAvailable;
    }
}
