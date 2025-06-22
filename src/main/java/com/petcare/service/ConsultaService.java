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

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepo;
    private final PetRepository petRepo;
    private final VeterinarioRepository veterinarioRepo;

    public List<ConsultaDTO> listar() {
        return consultaRepo.findAll().stream().map(this::toDTO).toList();
    }

    public ConsultaDTO salvar(ConsultaDTO dto) {
        Pet pet = petRepo.findById(dto.getIdPet()).orElseThrow(() ->
                new NoSuchElementException("Pet não encontrado"));
        Veterinario vet = veterinarioRepo.findById(dto.getIdVeterinario()).orElseThrow(() ->
                new NoSuchElementException("Veterinário não encontrado"));

        Consulta consulta = Consulta.builder()
                .id(dto.getId())
                .data(dto.getData())
                .descricao(dto.getDescricao())
                .observacoes(dto.getObservacoes())
                .remedios(dto.getRemedios())
                .pet(pet)
                .veterinario(vet)
                .build();

        return toDTO(consultaRepo.save(consulta));
    }

    public Optional<ConsultaDTO> buscarPorId(String id) {
        return consultaRepo.findById(id).map(this::toDTO);
    }

    public void excluir(String id) {
        consultaRepo.deleteById(id);
    }

    // Conversão Entidade → DTO
    private ConsultaDTO toDTO(Consulta c) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(c.getId());
        dto.setData(c.getData());
        dto.setDescricao(c.getDescricao());
        dto.setObservacoes(c.getObservacoes());
        dto.setRemedios(c.getRemedios());
        dto.setIdPet(c.getPet() != null ? c.getPet().getId() : null);
        dto.setIdVeterinario(c.getVeterinario() != null ? c.getVeterinario().getId() : null);
        return dto;
    }
}
