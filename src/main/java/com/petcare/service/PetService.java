package com.petcare.service;

import com.petcare.dto.PetDTO;
import com.petcare.entity.Pet;
import com.petcare.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    // Converte DTO para entidade Pet
    private Pet toEntity(PetDTO dto) {
        return Pet.builder()
                .id(dto.getId())
                .idTutor(dto.getIdTutor())
                .nome(dto.getNome())
                .especie(dto.getEspecie())
                .raca(dto.getRaca())
                .cor(dto.getCor())
                .idade(dto.getIdade())
                .sexo(dto.getSexo())
                .build();
    }

    // Converte entidade Pet para DTO
    private PetDTO toDTO(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .idTutor(pet.getIdTutor())
                .nome(pet.getNome())
                .especie(pet.getEspecie())
                .raca(pet.getRaca())
                .cor(pet.getCor())
                .idade(pet.getIdade())
                .sexo(pet.getSexo())
                .build();
    }

    public PetDTO salvar(PetDTO dto) {
        Pet pet = toEntity(dto);
        Pet salvo = petRepository.save(pet);
        return toDTO(salvo);
    }

    public List<PetDTO> listar() {
        return petRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PetDTO buscarPorId(String id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet não encontrado"));
        return toDTO(pet);
    }

    public void deletar(String id) {
        petRepository.deleteById(id);
    }

    public List<PetDTO> listarPorTutorId(String idTutor) {
        return petRepository.findByIdTutor(idTutor).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
