package com.petcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_pet", nullable = false)
    @NotNull
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "id_veterinario", nullable = false)
    @NotNull
    private Veterinario veterinario;

    @Column
    private String observacoes;

    @Column
    private List<String> remedios = new ArrayList<>();

}
