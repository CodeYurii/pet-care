package com.petcare.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ConsultaDTO {
    private String id;
    private LocalDateTime data;
    private String descricao;
    private String observacoes;
    private List<String> remedios = new ArrayList<>();
    private String idPet;
    private String idVeterinario;
}