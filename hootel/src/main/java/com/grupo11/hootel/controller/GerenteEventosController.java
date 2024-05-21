package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GerenteEventosController {

    private EventoService eventoService;

    public GerenteEventosController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/mostrarEventos")
    public String mostrarEventos(Model model){

        List<Evento> eventos = eventoService.lerTodosEventos();
        model.addAttribute("eventos", eventos);

        return "eventos_gerente";
    }

    @PostMapping("/addEvento")
    public String adicionarEvento(@Valid @ModelAttribute("evento") Evento evento,
                                  BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            return "eventos_gerente";
        }

        try {
            eventoService.criarEvento(evento);
        }catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "eventos_gerente";
        }

        return "redirect:/mostrarEventos";
    }

    @PostMapping("/removeEventos")
    public String removerEvento(@Valid @ModelAttribute("evento") Evento evento,
                                BindingResult bindingResult, Model model){

        return "redirect:/mostrarEventos";
    }
}
