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
        List<Pet> pets = dto.getPets() != null ? dto.getPets().stream().map(p -> Pet.builder()
                .nome(p.getNome())
                .especie(p.getEspecie())
                .raca(p.getRaca())
                .cor(p.getCor())
                .idade(p.getIdade())
                .sexo(p.getSexo())
                .build()
        ).collect(Collectors.toList()) : null;

        return Tutor.builder()
                .id((dto.getId()))
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .endereco(dto.getEndereco())
                .pets(pets)
                .build();
    }

    // Converte Entidade → DTO
    private TutorDTO toDTO(Tutor entity) {
        List<PetDTO> pets = entity.getPets() != null ? entity.getPets().stream().map(p -> {
            PetDTO dto = new PetDTO();
            dto.setNome(p.getNome());
            dto.setEspecie(p.getEspecie());
            dto.setRaca(p.getRaca());
            dto.setCor(p.getCor());
            dto.setIdade(p.getIdade());
            dto.setSexo(p.getSexo());
            return dto;
        }).collect(Collectors.toList()) : null;

        TutorDTO dto = new TutorDTO();
        dto.setId(String.valueOf(entity.getId()));
        dto.setNome(entity.getNome());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        dto.setEndereco(entity.getEndereco());
        dto.setPets(pets);
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
