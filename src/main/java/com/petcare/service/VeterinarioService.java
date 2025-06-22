package com.petcare.service;

import com.petcare.dto.VeterinarioDTO;
import com.petcare.entity.Veterinario;
import com.petcare.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeterinarioService {
    private final VeterinarioRepository repo;

    // Converte entidade para DTO
    private VeterinarioDTO toDTO(Veterinario v) {
        return VeterinarioDTO.builder()
                .id(v.getId())
                .nome(v.getNome())
                .cpf(v.getCpf())
                .crmv(v.getCrmv())
                .build();
    }

    // Converte DTO para entidade (considerando update)
    private Veterinario toEntity(VeterinarioDTO dto) {
        return Veterinario.builder()
                .id(dto.getId()) // importante para atualização
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .crmv(dto.getCrmv())
                .build();
    }

    public Veterinario salvar(VeterinarioDTO dto) {
        Veterinario v = toEntity(dto);
        return repo.save(v);
    }

    public void excluir(String id) {
        repo.deleteById(id);
    }

    public Optional<VeterinarioDTO> buscarPorId(String id) {
        return repo.findById(id)
                .map(v -> {
                    VeterinarioDTO dto = new VeterinarioDTO();
                    dto.setId(v.getId());
                    dto.setNome(v.getNome());
                    dto.setCpf(v.getCpf());
                    dto.setCrmv(v.getCrmv());
                    return dto;
                });
    }

    public List<VeterinarioDTO> listarTodos() {
        return repo.findAll().stream().map(v -> {
            VeterinarioDTO dto = new VeterinarioDTO();
            dto.setId(v.getId());
            dto.setNome(v.getNome());
            dto.setCpf(v.getCpf());
            dto.setCrmv(v.getCrmv());
            return dto;
        }).collect(Collectors.toList());
    }

}
