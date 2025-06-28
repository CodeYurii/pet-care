package com.petcare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "pet") // necessário para permitir a conversão para XML
public class PetDTO {

    @XmlElement
    private String id;

    @XmlElement
    private String idTutor;

    @XmlElement
    private String nome;

    @XmlElement
    private String especie;

    @XmlElement
    private String raca;

    @XmlElement
    private String cor;

    @XmlElement
    private Integer idade;

    @XmlElement
    private String sexo;
}