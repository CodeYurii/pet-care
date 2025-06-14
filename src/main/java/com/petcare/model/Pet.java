package com.petcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

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
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String especie;

    @Column
    private String raca;

    @Column
    private String cor;

    @Column
    private Integer idade;

    @Column
    private String sexo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    @NotNull
    private Tutor tutor;
}
