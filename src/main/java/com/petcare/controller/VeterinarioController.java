package com.petcare.controller;

import com.petcare.dto.VeterinarioDTO;
import com.petcare.service.VeterinarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private final VeterinarioService service;

    public VeterinarioController(VeterinarioService service) {
        this.service = service;
    }

    // Lista todos os veterinários
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("veterinarios", service.listarTodos());
        return "veterinarios/lista";
    }

    // Abre o formulário para novo veterinário
    @GetMapping("/novo")
    public String novoVeterinarioForm(Model model) {
        model.addAttribute("veterinario", new VeterinarioDTO());
        return "veterinarios/form";
    }

    // Abre o formulário para editar
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable String id, Model model) {
        Optional<VeterinarioDTO> dtoOptional = service.buscarPorId(id);
        if (dtoOptional.isPresent()) {
            model.addAttribute("veterinario", dtoOptional.get());
            return "veterinarios/form";
        } else {
            return "redirect:/veterinarios?erro=nao-encontrado";
        }
    }

    // Salva ou atualiza veterinário
    @PostMapping
    public String salvar(@ModelAttribute("veterinario") @Valid VeterinarioDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return "veterinarios/form";
        }
        service.salvar(dto);
        return "redirect:/veterinarios";
    }

    // Exclui veterinário
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable String id) {
        service.excluir(id);
        return "redirect:/veterinarios";
    }
}
