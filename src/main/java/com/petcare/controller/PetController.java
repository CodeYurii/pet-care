package com.petcare.controller;

import com.petcare.dto.PetDTO;
import com.petcare.entity.Pet;
import com.petcare.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<PetDTO> salvar(@RequestBody PetDTO petDTO) {
        PetDTO salvo = petService.salvar(petDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<PetDTO> listar() {
        return petService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> buscar(@PathVariable String id) {
        PetDTO pet = petService.buscarPorId(id);
        return ResponseEntity.ok(pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        petService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tutor/{tutorId}")
    public List<PetDTO> listarPorTutor(@PathVariable String tutorId) {
        return petService.listarPorTutorId(tutorId);
    }
}
