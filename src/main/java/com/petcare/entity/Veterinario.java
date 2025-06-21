package com.petcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "veterinarios")
public class Veterinario {

    @Id
    private String id;

    @NotNull
    private String nome;

    @NotNull
    private String cpf;

    @NotNull
    private Integer crmv;

}
