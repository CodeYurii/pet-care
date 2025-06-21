package com.petcare.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsultaDTO {
    private String id;
    private LocalDateTime data;
    private String descricao;
    private String observacoes;
    private String idPet;
    private String idVeterinario;
}
