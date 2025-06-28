package com.petcare.dto;

import com.petcare.entity.Pet;
import com.petcare.entity.Veterinario;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class ConsultaDTO {

    private String idConsulta;


    private LocalDateTime data;


    private String descricao;


    private String observacoes;

    private List<String> remedios = new ArrayList<>();


    private String idPet;

    private String idVeterinario;


    private Pet pet;

    private Veterinario veterinario;
}
