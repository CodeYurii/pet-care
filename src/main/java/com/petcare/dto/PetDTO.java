package com.petcare.dto;

import lombok.Data;

@Data
public class PetDTO {
    private String nome;
    private String especie;
    private String raca;
    private String cor;
    private Integer idade;
    private String sexo;
}
