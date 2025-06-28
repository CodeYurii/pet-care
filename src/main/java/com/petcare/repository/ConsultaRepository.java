package com.petcare.repository;

import com.petcare.entity.Consulta;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConsultaRepository extends MongoRepository<Consulta, String> {
}
