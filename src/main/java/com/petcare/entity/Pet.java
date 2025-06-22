package com.petcare.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Gera métodos getters, setters, to strings, equals and hashcode)
@Data

// Gera o padrão builder
@Builder

// Cria um construtor com todos os atributos da classe
@AllArgsConstructor

// Cria um construtor vazio (sem argumentos)
@NoArgsConstructor

@Document(collection = "pets")
public class Pet {
    @Id
    private String id;

    private String nome;
    private String especie;
    private String raca;
    private String cor;
    private Integer idade;
    private String sexo;

    @NotBlank
    private String idTutor;
}
