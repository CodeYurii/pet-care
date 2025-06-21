package com.petcare.repository;

import com.petcare.entity.Veterinario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VeterinarioRepository extends MongoRepository<Veterinario, String> {
}
