package com.petcare.dto;

import lombok.Data;
import java.util.List;

@Data
public class TutorDTO {
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private List<PetDTO> pets;
}
