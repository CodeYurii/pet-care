package com.petcare.service;

import com.petcare.dto.ConsultaDTO;
import com.petcare.entity.Consulta;
import com.petcare.entity.Pet;
import com.petcare.entity.Veterinario;
import com.petcare.repository.ConsultaRepository;
import com.petcare.repository.PetRepository;
import com.petcare.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócio relacionada às consultas veterinárias.
 * <p>
 * Esta classe implementa o padrão de projeto Service, que encapsula a lógica de negócio
 * e serve como uma camada intermediária entre os controladores e os repositórios.
 * O serviço é responsável por operações como listar, salvar, buscar e excluir consultas,
 * além de realizar a conversão entre entidades e DTOs.
 * <p>
 * A classe utiliza injeção de dependência através do construtor gerado pelo Lombok
 * (@RequiredArgsConstructor) para receber os repositórios necessários.
 *
 * @author Estudante de Programação
 * @version 1.0
 * @see com.petcare.entity.Consulta
 * @see com.petcare.dto.ConsultaDTO
 * @see com.petcare.repository.ConsultaRepository
 */
@Service
@RequiredArgsConstructor
public class ConsultaService {

    /**
     * Repositório de consultas utilizado para operações de persistência de consultas.
     * Fornece métodos para salvar, buscar, listar e excluir consultas no banco de dados.
     */
    private final ConsultaRepository consultaRepo;

    /**
     * Repositório de pets utilizado para buscar informações de pets
     * necessárias para a criação e edição de consultas.
     */
    private final PetRepository petRepo;

    /**
     * Repositório de veterinários utilizado para buscar informações de veterinários
     * necessárias para a criação e edição de consultas.
     */
    private final VeterinarioRepository veterinarioRepo;

    /**
     * Lista todas as consultas cadastradas no sistema.
     * <p>
     * Este método busca todas as consultas no banco de dados através do repositório
     * e converte cada entidade Consulta para um objeto ConsultaDTO utilizando o método
     * toDTO. A conversão é realizada utilizando a API de Stream do Java.
     *
     * @return Uma lista de objetos ConsultaDTO representando todas as consultas cadastradas
     */
    public List<ConsultaDTO> listar() {
        return consultaRepo.findAll().stream().map(this::toDTO).toList();
    }

    /**
     * Salva uma nova consulta ou atualiza uma consulta existente.
     * <p>
     * Este método recebe um objeto ConsultaDTO contendo os dados da consulta a ser salva.
     * Ele busca o pet e o veterinário associados à consulta pelos seus IDs e, se encontrados,
     * cria um objeto Consulta utilizando o padrão Builder. O objeto é então salvo no banco
     * de dados através do repositório e convertido de volta para um DTO antes de ser retornado.
     * <p>
     * Se o pet ou o veterinário não forem encontrados, uma exceção NoSuchElementException
     * é lançada com uma mensagem apropriada.
     *
     * @param dto O objeto DTO contendo os dados da consulta a ser salva
     * @return Um objeto ConsultaDTO representando a consulta salva
     * @throws NoSuchElementException Se o pet ou o veterinário não forem encontrados
     */
    public ConsultaDTO salvar(ConsultaDTO dto) {
        Pet pet = petRepo.findById(dto.getIdPet()).orElseThrow(() ->
                new NoSuchElementException("Pet não encontrado"));
        Veterinario vet = veterinarioRepo.findById(dto.getIdVeterinario()).orElseThrow(() ->
                new NoSuchElementException("Veterinário não encontrado"));

        Consulta consulta = Consulta.builder()
                .id(dto.getIdConsulta())
                .data(dto.getData())
                .descricao(dto.getDescricao())
                .observacoes(dto.getObservacoes())
                .remedios(dto.getRemedios())
                .pet(pet)
                .veterinario(vet)
                .build();

        return toDTO(consultaRepo.save(consulta));
    }

    /**
     * Busca uma consulta pelo seu ID.
     * <p>
     * Este método busca uma consulta no banco de dados pelo seu ID utilizando o repositório.
     * Se a consulta for encontrada, ela é convertida para um objeto ConsultaDTO utilizando
     * o método toDTO. O resultado é encapsulado em um Optional para lidar com o caso em que
     * a consulta não é encontrada.
     *
     * @param id O identificador único da consulta a ser buscada
     * @return Um Optional contendo o objeto ConsultaDTO se a consulta for encontrada,
     *         ou um Optional vazio caso contrário
     */
    public Optional<ConsultaDTO> buscarPorId(String id) {
        return consultaRepo.findById(id).map(this::toDTO);
    }

    /**
     * Exclui uma consulta pelo seu ID.
     * <p>
     * Este método exclui uma consulta do banco de dados pelo seu ID utilizando o repositório.
     * Se a consulta não for encontrada, o método não faz nada.
     *
     * @param id O identificador único da consulta a ser excluída
     */
    public void excluir(String id) {
        consultaRepo.deleteById(id);
    }

    /**
     * Converte uma entidade Consulta para um objeto ConsultaDTO.
     * <p>
     * Este método privado é utilizado internamente para converter entidades Consulta
     * em objetos ConsultaDTO, que são mais adequados para transferência de dados entre
     * camadas da aplicação. A conversão inclui a cópia de todos os atributos da entidade
     * para o DTO, incluindo referências para o pet e o veterinário associados à consulta.
     * <p>
     * Além dos IDs do pet e do veterinário, o método também define os objetos completos
     * no DTO para facilitar a exibição de informações detalhadas na interface do usuário.
     *
     * @param c A entidade Consulta a ser convertida
     * @return Um objeto ConsultaDTO contendo os dados da consulta
     */
    private ConsultaDTO toDTO(Consulta c) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setIdConsulta(c.getId());
        dto.setData(c.getData());
        dto.setDescricao(c.getDescricao());
        dto.setObservacoes(c.getObservacoes());
        dto.setRemedios(c.getRemedios());
        dto.setIdPet(c.getPet() != null ? c.getPet().getId() : null);
        dto.setIdVeterinario(c.getVeterinario() != null ? c.getVeterinario().getId() : null);

        // Set the pet and veterinario objects for display in the list view
        dto.setPet(c.getPet());
        dto.setVeterinario(c.getVeterinario());

        return dto;
    }
}
