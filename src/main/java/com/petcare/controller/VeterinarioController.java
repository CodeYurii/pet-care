package com.petcare.controller;

import com.petcare.dto.VeterinarioDTO;
import com.petcare.service.VeterinarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private final VeterinarioService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("veterinarios", service.listar());
        return "veterinarios/lista";
    }

    @PostMapping
    public String salvar(@ModelAttribute VeterinarioDTO dto) {
        service.salvar(dto);
        return "redirect:/veterinarios";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("veterinario", new VeterinarioDTO());
        return "veterinarios/form";
    }

}
