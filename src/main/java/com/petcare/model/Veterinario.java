package com.petcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Gera métodos getters, setters, to strings, equals and hashcode
@Data

// A classe é uma entidade
@Entity

// Gera o padrão builder
@Builder

// Cria um construtor com todos os atributos da classe
@AllArgsConstructor

// Cria um construtor vazio (sem argumentos)
@NoArgsConstructor
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    // Numero do conselho regional de medicina veterinária
    @Column(nullable = false)
    private Integer crmv;

}
