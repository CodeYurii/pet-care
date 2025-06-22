package com.petcare.repository;

import com.petcare.entity.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PetRepository extends MongoRepository<Pet, String> {
    List<Pet> findByIdTutor(String idTutor);
}
