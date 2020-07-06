package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;

    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pets = new ArrayList<>();

    public Schedule() {
    }

    public Schedule(long id, LocalDate date, Set<EmployeeSkill> activities, List<Employee> employees, List<Pet> pets) {
        this.id = id;
        this.date = date;
        this.activities = activities;
        this.employees = employees;
        this.pets = pets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return getId() == schedule.getId() &&
                Objects.equals(getDate(), schedule.getDate()) &&
                Objects.equals(getActivities(), schedule.getActivities()) &&
                Objects.equals(getEmployees(), schedule.getEmployees()) &&
                Objects.equals(getPets(), schedule.getPets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getActivities(), getEmployees(), getPets());
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", date=" + date +
                ", activities=" + activities +
                ", employees=" + employees +
                ", pets=" + pets +
                '}';
    }
}
