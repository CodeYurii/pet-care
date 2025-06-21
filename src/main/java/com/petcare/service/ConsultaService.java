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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepo;
    private final PetRepository petRepo;
    private final VeterinarioRepository veterinarioRepo;

    public List<Consulta> listar() {
        return consultaRepo.findAll();
    }

    public Consulta salvar(ConsultaDTO dto) {
        Pet pet = petRepo.findById(dto.getIdPet()).orElseThrow();
        Veterinario vet = veterinarioRepo.findById(dto.getIdVeterinario()).orElseThrow();

        Consulta consulta = Consulta.builder()
                .data(dto.getData())
                .descricao(dto.getDescricao())
                .observacoes(dto.getObservacoes())
                .pet(pet)
                .veterinario(vet)
                .build();

        return consultaRepo.save(consulta);
    }

    public Optional<Consulta> buscarPorId(String id) {
        return consultaRepo.findById(id);
    }

    public void excluir(String id) {
        consultaRepo.deleteById(id);
    }
}