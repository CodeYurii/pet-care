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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "consultas")
public class Consulta {

    @Id
    private String id;

    @NotNull
    private LocalDateTime data;

    private String descricao;


    @DBRef
    @NotNull
    private Pet pet;

    @DBRef
    @NotNull
    private Veterinario veterinario;

    private String observacoes;

    private List<String> remedios = new ArrayList<>();
}
