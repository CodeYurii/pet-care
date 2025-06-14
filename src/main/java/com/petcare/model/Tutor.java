package com.petcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Gera métodos getters, setters, to strings, equals and hashcode)
@Data

// A classe é uma entidade
@Entity

// Gera o padrão builder
@Builder

// Cria um construtor com todos os atributos da classe
@AllArgsConstructor

// Cria um construtor vazio (sem argumentos)
@NoArgsConstructor
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column
    private List<String> telefones = new ArrayList<>();

    @Column
    private String endereco;
}
