package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.dto.TranslatorDTO;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    TranslatorDTO translatorDTO;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        List<Object> scheduleDetails = translatorDTO.getSchedule(scheduleDTO);
        Schedule schedule = scheduleService.createSchedule((Schedule) scheduleDetails.get(0), (List<Long>) scheduleDetails.get(1), (List<Long>)scheduleDetails.get(2));
        return translatorDTO.getScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAll();
        return translatorDTO.getScheduleDTOs(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable Long petId) {
        List<Schedule> schedules = scheduleService.getSchedulesByPetId(petId);
        return translatorDTO.getScheduleDTOs(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable Long employeeId) {
        List<Schedule> schedules = scheduleService.getSchedulesByEmployeeId(employeeId);
        return translatorDTO.getScheduleDTOs(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable Long customerId) {
        List<Schedule> schedules = scheduleService.getSchedulesByCustomerId(customerId);
        return translatorDTO.getScheduleDTOs(schedules);
    }
}
