package com.petcare.service;

import com.petcare.entity.Pet;
import com.petcare.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> listar() {
        return petRepository.findAll();
    }

    public Pet buscarPorId(Long id) {
        return petRepository.findById(String.valueOf(id)).orElseThrow();
    }

    public void deletar(Long id) {
        petRepository.deleteById(String.valueOf(id));
    }
}
