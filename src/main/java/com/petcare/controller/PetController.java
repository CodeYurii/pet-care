package com.petcare.controller;

import com.petcare.dto.PetDTO;
import com.petcare.entity.Pet;
import com.petcare.repository.PetRepository;
import com.petcare.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetRepository petRepository;
    private final PetService petService;

    @Autowired
    public PetController(PetRepository petRepository, PetService petService) {
        this.petRepository = petRepository;
        this.petService = petService;
    }

    // Listar pets na página HTML
    @GetMapping
    public String listarPets(Model model) {
        List<Pet> pets = petRepository.findAll();
        model.addAttribute("pets", pets);
        return "pets/lista";
    }

    // Exibir formulário para novo pet
    @GetMapping("/novo")
    public String novoPetForm(Model model) {
        model.addAttribute("pet", new Pet());  // Criar objeto vazio para bind no formulário
        return "pets/form";
    }

    // Salvar pet vindo do formulário
    @PostMapping
    public String salvar(@ModelAttribute Pet pet) {
        petRepository.save(pet);
        return "redirect:/pets";  // Após salvar, redireciona para lista de pets
    }

    // Métodos REST (JSON) para uso em API - opcionais
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PetDTO> buscar(@PathVariable String id) {
        PetDTO pet = petService.buscarPorId(id);
        return ResponseEntity.ok(pet);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        petService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tutor/{tutorId}")
    @ResponseBody
    public List<PetDTO> listarPorTutor(@PathVariable String tutorId) {
        return petService.listarPorTutorId(tutorId);
    }
}
