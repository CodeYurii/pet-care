package com.petcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa uma consulta veterinária no sistema.
 * <p>
 * Esta classe é mapeada para a coleção "consultas" no banco de dados MongoDB
 * e contém informações sobre consultas veterinárias, incluindo data, descrição,
 * observações, remédios prescritos, e referências para o pet e o veterinário
 * envolvidos na consulta.
 * <p>
 * A classe utiliza as anotações do Lombok para gerar automaticamente métodos
 * como getters, setters, equals, hashCode e toString, além de construtores e
 * um builder para facilitar a criação de instâncias.
 *
 * @author Estudante de Programação
 * @version 1.0
 * @see Pet
 * @see Veterinario
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("consultas")
public class Consulta {
    /**
     * Identificador único da consulta.
     * Gerado automaticamente pelo MongoDB.
     */
    @Id
    private String id;

    /**
     * Data e hora da consulta.
     * Armazenada como um objeto LocalDateTime do Java.
     */
    private LocalDateTime data;

    /**
     * Descrição breve da consulta.
     * Contém informações sobre o motivo da consulta.
     */
    private String descricao;

    /**
     * Observações detalhadas feitas pelo veterinário durante a consulta.
     * Pode conter informações sobre o diagnóstico, tratamento, etc.
     */
    private String observacoes;

    /**
     * Lista de remédios prescritos durante a consulta.
     * Cada elemento da lista é uma string representando um remédio.
     */
    private List<String> remedios = new ArrayList<>();

    /**
     * Referência ao pet que foi atendido na consulta.
     * Utiliza a anotação @DBRef para criar uma referência ao documento do pet no MongoDB.
     */
    @DBRef
    private Pet pet;

    /**
     * Referência ao veterinário que realizou a consulta.
     * Utiliza a anotação @DBRef para criar uma referência ao documento do veterinário no MongoDB.
     */
    @DBRef
    private Veterinario veterinario;
}
