package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.dto.TranslatorDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    TranslatorDTO translatorDTO;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        List<Object> petDetails = translatorDTO.getPet(petDTO);
        Pet pet = petService.createPet((Pet)petDetails.get(0), (Long)petDetails.get(1));
        return translatorDTO.getPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {
        Pet pet = petService.getPetById(petId);
        return translatorDTO.getPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAllPets();
        return translatorDTO.getPetDTOs(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
        List<Pet> pets = petService.getByOwnerId(ownerId);
        return translatorDTO.getPetDTOs(pets);
    }
}
