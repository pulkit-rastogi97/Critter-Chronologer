package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    @Value("${not.found.pet}")
    String petError;

    public Customer createCustomer(Customer customer, List<Long> petIds) {
        List<Pet> pets;
        if(!isNullOrEmpty(petIds))
        {
            pets = petRepository.findAllById(petIds);
            customer.setPets(pets);
        }else
            customer.setPets(new ArrayList<>());
        System.out.println("Before saving  Customer : " + customer);
        Customer savedCustomer = customerRepository.saveAndFlush(customer);
        System.out.println("After saving  Customer : " + savedCustomer);
        return  savedCustomer;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getOwnerByPetId(Long petId) {
        if(petId == null || petId <= 0)
            throw new PetNotFoundException(petError);
        Optional<Pet> pet = petRepository.findById(petId);
        if(pet.isPresent())
            return pet.get().getOwner();
        else
            throw new PetNotFoundException(petError);
    }

    boolean isNullOrEmpty(List<?> value)
    {
        return value == null || value.isEmpty();
    }
}
