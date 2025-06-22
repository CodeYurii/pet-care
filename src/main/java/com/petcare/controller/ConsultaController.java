package com.petcare.controller;

import com.petcare.dto.ConsultaDTO;
import com.petcare.entity.Consulta;
import com.petcare.repository.PetRepository;
import com.petcare.repository.VeterinarioRepository;
import com.petcare.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;
    private final PetRepository petRepo;
    private final VeterinarioRepository veterinarioRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("consultas", service.listar());
        return "consultas/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("consulta", new ConsultaDTO());
        model.addAttribute("pets", petRepo.findAll());
        model.addAttribute("veterinarios", veterinarioRepo.findAll());
        return "consultas/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable String id, Model model) {
        Optional<ConsultaDTO> dto = service.buscarPorId(id);
        if (dto.isPresent()) {
            model.addAttribute("consulta", dto.get());
            model.addAttribute("pets", petRepo.findAll());
            model.addAttribute("veterinarios", veterinarioRepo.findAll());
            return "consultas/form";
        } else {
            return "redirect:/consultas?erro=nao-encontrada";
        }
    }

    @PostMapping
    public String salvar(@ModelAttribute ConsultaDTO dto) {
        service.salvar(dto);
        return "redirect:/consultas";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable String id) {
        service.excluir(id);
        return "redirect:/consultas";
    }
}
