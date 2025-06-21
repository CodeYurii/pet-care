package com.petcare.controller;

import com.petcare.dto.ConsultaDTO;
import com.petcare.entity.Consulta;
import com.petcare.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    @GetMapping
    public String listar(Model model) {
        List<Consulta> consultas = service.listar();
        model.addAttribute("consultas", consultas);
        return "consultas/lista";
    }

    @PostMapping
    public String salvar(@ModelAttribute ConsultaDTO dto) {
        service.salvar(dto);
        return "redirect:/consultas";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("consulta", new ConsultaDTO());
        return "consultas/form";
    }
}
