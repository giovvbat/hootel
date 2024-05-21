package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.exceptions.PinInvalidoException;
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

    public String popularModal(Model model){

        model.addAttribute("evento", new Evento());
        model.addAttribute("eventoRemover", new Evento());
        model.addAttribute("eventoAtualizar", new Evento());
        try {
            List<Evento> eventos = eventoService.lerTodosEventos();
            model.addAttribute("eventos", eventos);
            return "eventos_gerente";
        }catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "add_evento_gerente";
        }
    }

    @GetMapping("/mostrarEventos")
    public String mostrarEventos(Model model){
        return popularModal(model);
    }

    @PostMapping("/addEvento")
    public String adicionarEvento(@Valid @ModelAttribute("evento") Evento evento,
                                  BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            return popularModal(model);
        }

        try {
            eventoService.criarEvento(evento);
        }catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "eventos_gerente";
        }

        return "redirect:/mostrarEventos";
    }

    @PostMapping("/removeEvento")
    public String removerEvento(@ModelAttribute("evento") Evento evento,
                                 BindingResult bindingResult,
                                 Model model){

        if (bindingResult.hasErrors()) {
            return popularModal(model);
        }


        try {
            eventoService.deletarEvento(evento.getId());
        }catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "eventos_gerente";
        }
        return "redirect:/mostrarEventos";
    }

    @PostMapping("/atualizaEvento")
    public String atualizarEvento(@Valid @ModelAttribute("eventoAtualizar") Evento evento,
                                BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            return popularModal(model);
        }

        try {
            eventoService.atualizarEvento(evento);
        }catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "eventos_gerente";
        }

        return "redirect:/mostrarEventos";
    }
}
