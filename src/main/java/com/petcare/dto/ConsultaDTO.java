package com.petcare.dto;

import com.petcare.entity.Pet;
import com.petcare.entity.Veterinario;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de Transferência de Dados (DTO) para a entidade Consulta.
 * <p>
 * Esta classe é utilizada para transferir dados de consultas entre as camadas da aplicação,
 * especialmente entre o controlador e a camada de serviço, e entre a camada de serviço e a
 * interface do usuário. Ela contém todos os campos necessários para exibir, criar e editar
 * consultas, sem expor diretamente a entidade de domínio.
 * <p>
 * O DTO inclui tanto os IDs do pet e do veterinário quanto os objetos completos,
 * permitindo que a interface do usuário exiba informações detalhadas sem precisar
 * fazer consultas adicionais ao banco de dados.
 * <p>
 * A classe utiliza a anotação @Data do Lombok para gerar automaticamente métodos
 * como getters, setters, equals, hashCode e toString.
 *
 * @author Estudante de Programação
 * @version 1.0
 * @see com.petcare.entity.Consulta
 * @see com.petcare.entity.Pet
 * @see com.petcare.entity.Veterinario
 */
@Data
public class ConsultaDTO {
    /**
     * Identificador único da consulta.
     * Corresponde ao campo id da entidade Consulta.
     */
    private String idConsulta;

    /**
     * Data e hora da consulta.
     * Armazenada como um objeto LocalDateTime do Java.
     */
    private LocalDateTime data;

    /**
     * Descrição breve da consulta.
     * Contém informações sobre o motivo da consulta.
     */
    private String descricao;

    /**
     * Observações detalhadas feitas pelo veterinário durante a consulta.
     * Pode conter informações sobre o diagnóstico, tratamento, etc.
     */
    private String observacoes;

    /**
     * Lista de remédios prescritos durante a consulta.
     * Cada elemento da lista é uma string representando um remédio.
     */
    private List<String> remedios = new ArrayList<>();

    /**
     * Identificador único do pet atendido na consulta.
     * Utilizado para buscar o pet no banco de dados.
     */
    private String idPet;

    /**
     * Identificador único do veterinário que realizou a consulta.
     * Utilizado para buscar o veterinário no banco de dados.
     */
    private String idVeterinario;

    /**
     * Objeto Pet completo associado à consulta.
     * Utilizado para exibir informações detalhadas do pet na interface do usuário,
     * sem necessidade de consultas adicionais ao banco de dados.
     */
    private Pet pet;

    /**
     * Objeto Veterinario completo associado à consulta.
     * Utilizado para exibir informações detalhadas do veterinário na interface do usuário,
     * sem necessidade de consultas adicionais ao banco de dados.
     */
    private Veterinario veterinario;
}
