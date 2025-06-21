package com.petcare.entity;

import lombok.*;
// Gera métodos getters, setters, to strings, equals and hashcode)
@Data

// Gera o padrão builder
@Builder

// Cria um construtor com todos os atributos da classe
@AllArgsConstructor

// Cria um construtor vazio (sem argumentos)
@NoArgsConstructor
public class Pet {

    private String nome;
    private String especie;
    private String raca;
    private String cor;
    private Integer idade;
    private String sexo;
}
