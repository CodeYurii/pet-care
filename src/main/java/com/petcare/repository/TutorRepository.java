package com.petcare.repository;

import com.petcare.entity.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TutorRepository extends MongoRepository<Tutor, String> {
    List<Tutor> findByNomeContainingIgnoreCase(String nome);
}
