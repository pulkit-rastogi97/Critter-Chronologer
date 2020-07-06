package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String notes;
    private String name;

    @OneToMany(targetEntity = Pet.class, mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long id, String phoneNumber, String notes, String name, List<Pet> pets) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.name = name;
        this.pets = pets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId()) &&
                Objects.equals(getPhoneNumber(), customer.getPhoneNumber()) &&
                Objects.equals(getNotes(), customer.getNotes()) &&
                Objects.equals(getName(), customer.getName()) &&
                Objects.equals(getPets(), customer.getPets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhoneNumber(), getNotes(), getName(), getPets());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", name='" + name + '\'' +
                ", pets=" + pets +
                '}';
    }
}
