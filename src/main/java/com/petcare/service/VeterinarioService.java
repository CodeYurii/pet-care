package com.petcare.service;

import com.petcare.dto.VeterinarioDTO;
import com.petcare.entity.Veterinario;
import com.petcare.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarioService {
    private final VeterinarioRepository repo;

    public List<Veterinario> listar() {
        return repo.findAll();
    }

    public Veterinario salvar(VeterinarioDTO dto) {
        Veterinario v = Veterinario.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .crmv(dto.getCrmv())
                .build();
        return repo.save(v);
    }

    public void excluir(String id) {
        repo.deleteById(id);
    }
}