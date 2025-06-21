package com.petcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("veterinarios")
public class Veterinario {
    @Id
    private String id;
    private String nome;
    private String cpf;
    private Integer crmv;
}