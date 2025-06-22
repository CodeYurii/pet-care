package com.petcare.service;

import com.petcare.dto.PetDTO;
import com.petcare.dto.TutorDTO;
import com.petcare.entity.Pet;
import com.petcare.entity.Tutor;
import com.petcare.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    // Converte DTO → Entidade
    private Tutor toEntity(TutorDTO dto) {
        return Tutor.builder()
                .id((dto.getId()))
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .endereco(dto.getEndereco())
                .build();
    }

    // Converte Entidade → DTO
    private TutorDTO toDTO(Tutor entity) {
        TutorDTO dto = new TutorDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        dto.setEndereco(entity.getEndereco());
        dto.setPets(null);
        return dto;
    }


    // Lista todos os tutores (DTOs)
    public List<TutorDTO> listarTodos() {
        return tutorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Busca tutor por ID
    public Optional<TutorDTO> buscarPorId(String id) {
        return tutorRepository.findById(id).map(this::toDTO);
    }

    // Salva tutor
    public TutorDTO salvar(TutorDTO dto) {
        Tutor entity = toEntity(dto);
        Tutor salvo = tutorRepository.save(entity);
        return toDTO(salvo);
    }

    // Remove tutor
    public void deletar(String id) {
        tutorRepository.deleteById(id);
    }
}
