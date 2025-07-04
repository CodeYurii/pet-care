package com.petcare.controller;

import com.petcare.dto.TutorDTO;
import com.petcare.entity.Tutor;
import com.petcare.repository.TutorRepository;
import com.petcare.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private TutorService tutorService;

    @GetMapping
    public String listar(Model model) {
        List<Tutor> tutores = tutorRepository.findAll();
        model.addAttribute("tutores", tutores);
        return "tutores/tutores"; // templates/tutores.html
    }

    @GetMapping("/novo")
    public String novoTutorForm(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutores/novo-tutor";
    }

    @PostMapping
    public String salvar(@ModelAttribute TutorDTO dto) {
        tutorService.salvar(dto);
        return "redirect:/tutores";
    }

}
