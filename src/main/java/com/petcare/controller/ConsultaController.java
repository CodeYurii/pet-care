package com.petcare.controller;

import com.petcare.dto.ConsultaDTO;
import com.petcare.repository.PetRepository;
import com.petcare.repository.VeterinarioRepository;
import com.petcare.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controlador responsável por gerenciar as operações relacionadas às consultas veterinárias.
 * <p>
 * Este controlador implementa o padrão MVC (Model-View-Controller) e utiliza o Spring MVC
 * para mapear requisições HTTP para métodos específicos. Ele gerencia as operações CRUD
 * (Create, Read, Update, Delete) para a entidade Consulta.
 * <p>
 * O controlador utiliza o caminho base "/consultas" para todas as suas operações e
 * depende de serviços e repositórios para executar a lógica de negócio.
 * 
 * @author Estudante de Programação
 * @version 1.0
 * @see com.petcare.entity.Consulta
 * @see com.petcare.service.ConsultaService
 * @see com.petcare.dto.ConsultaDTO
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/consultas")
public class ConsultaController {

    /**
     * Serviço responsável pela lógica de negócio relacionada às consultas.
     * Utilizado para operações como listar, salvar, buscar e excluir consultas.
     */
    private final ConsultaService service;

    /**
     * Repositório de pets utilizado para buscar informações de pets
     * necessárias para a criação e edição de consultas.
     */
    private final PetRepository petRepo;

    /**
     * Repositório de veterinários utilizado para buscar informações de veterinários
     * necessárias para a criação e edição de consultas.
     */
    private final VeterinarioRepository veterinarioRepo;

    /**
     * Lista todas as consultas cadastradas no sistema.
     * <p>
     * Este método responde a requisições GET para o caminho "/consultas".
     * Ele busca todas as consultas através do serviço e adiciona a lista ao modelo,
     * que será utilizado pela view para exibir as informações.
     *
     * @param model O modelo utilizado para passar dados para a view
     * @return O nome da view que será renderizada (consultas/lista)
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("consultas", service.listar());
        return "consultas/lista";
    }

    /**
     * Exibe o formulário para criação de uma nova consulta.
     * <p>
     * Este método responde a requisições GET para o caminho "/consultas/novo".
     * Ele prepara o modelo com um novo objeto ConsultaDTO vazio e adiciona as listas
     * de pets e veterinários disponíveis para seleção no formulário.
     *
     * @param model O modelo utilizado para passar dados para a view
     * @return O nome da view que será renderizada (consultas/form)
     */
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("consulta", new ConsultaDTO());
        model.addAttribute("pets", petRepo.findAll());
        model.addAttribute("veterinarios", veterinarioRepo.findAll());
        return "consultas/form";
    }

    /**
     * Exibe o formulário para edição de uma consulta existente.
     * <p>
     * Este método responde a requisições GET para o caminho "/consultas/{id}/editar".
     * Ele busca a consulta pelo ID fornecido e, se encontrada, prepara o modelo com os
     * dados da consulta e as listas de pets e veterinários disponíveis para seleção.
     * Se a consulta não for encontrada, redireciona para a listagem de consultas.
     *
     * @param id O identificador único da consulta a ser editada
     * @param model O modelo utilizado para passar dados para a view
     * @return O nome da view que será renderizada (consultas/form) ou um redirecionamento
     */
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable String id, Model model) {
        Optional<ConsultaDTO> dto = service.buscarPorId(id);
        if (dto.isPresent()) {
            model.addAttribute("consulta", dto.get());
            model.addAttribute("pets", petRepo.findAll());
            model.addAttribute("veterinarios", veterinarioRepo.findAll());
            return "consultas/form";
        } else {
            return "redirect:/consultas";
        }
    }

    /**
     * Salva uma nova consulta ou atualiza uma consulta existente.
     * <p>
     * Este método responde a requisições POST para o caminho "/consultas".
     * Ele recebe os dados do formulário como um objeto ConsultaDTO e uma string
     * contendo os remédios separados por vírgula. A string de remédios é processada
     * para criar uma lista de remédios que é adicionada ao DTO antes de salvá-lo.
     * <p>
     * Após salvar a consulta, o usuário é redirecionado para a listagem de consultas.
     *
     * @param dto O objeto DTO contendo os dados da consulta a ser salva
     * @param remediosStr Uma string contendo os remédios separados por vírgula (opcional)
     * @return Um redirecionamento para a listagem de consultas
     */
    @PostMapping
    public String salvar(@ModelAttribute ConsultaDTO dto, @RequestParam(name = "remedios", required = false) String remediosStr) {
        if (remediosStr != null && !remediosStr.trim().isEmpty()) {
            String[] remediosArray = remediosStr.split(",");
            List<String> remediosList = new ArrayList<>();
            for (String remedio : remediosArray) {
                String trimmed = remedio.trim();
                if (!trimmed.isEmpty()) {
                    remediosList.add(trimmed);
                }
            }
            dto.setRemedios(remediosList);
        }
        service.salvar(dto);
        return "redirect:/consultas";
    }

    /**
     * Exclui uma consulta existente.
     * <p>
     * Este método responde a requisições POST para o caminho "/consultas/{id}/excluir".
     * Ele recebe o ID da consulta a ser excluída como um parâmetro de caminho e utiliza
     * o serviço para realizar a exclusão. Após a exclusão, o usuário é redirecionado
     * para a listagem de consultas.
     *
     * @param id O identificador único da consulta a ser excluída
     * @return Um redirecionamento para a listagem de consultas
     */
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable String id) {
        service.excluir(id);
        return "redirect:/consultas";
    }
}
