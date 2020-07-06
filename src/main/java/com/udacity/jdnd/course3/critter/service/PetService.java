package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Value("${not.found.owner}")
    String ownerError;

    @Value("${not.found.pet}")
    String petError;

    public Pet createPet(Pet pet, Long ownerId) {
        if(ownerId == null || ownerId<=0)
            throw new CustomerNotFoundException(ownerError);
        Optional<Customer> owner = customerRepository.findById(ownerId);
        if(owner.isPresent()) {
            pet.setOwner(owner.get());
            Pet newPet = petRepository.saveAndFlush(pet);
            owner.get().getPets().add(pet);
            customerRepository.saveAndFlush(owner.get());
            return newPet;
        }else
            throw new CustomerNotFoundException(ownerError);
    }

    public Pet getPetById(Long petId) {
        if(petId == null || petId <= 0)
            throw new PetNotFoundException(petError);
        Optional<Pet> pet = petRepository.findById(petId);
        if(pet.isPresent()) {
            return petRepository.getOne(petId);
        }else
            throw new PetNotFoundException(petError);
    }

    public List<Pet> getAllPets() {
       return  petRepository.findAll();
    }

    public List<Pet> getByOwnerId(Long ownerId) {
        if(ownerId == null || ownerId<=0)
            throw new CustomerNotFoundException(ownerError);
        return petRepository.findAllByOwnerId(ownerId);
    }

}
