package com.petcare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDTO {
    private String id;
    private String idTutor;
    private String nome;
    private String especie;
    private String raca;
    private String cor;
    private Integer idade;
    private String sexo;
}