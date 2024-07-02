package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.EventoHotel;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GerenteEventosHotelController {

    private EventoService eventoService;

    public GerenteEventosHotelController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    public String popularModel(Model model){
        try {
            List<Evento> eventos = eventoService.lerTodosEventos(EventoHotel.class);
            model.addAttribute("eventos", eventos);
            return "eventos_gerente";
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("eventos", new ArrayList<Evento>());
            return "add_evento_gerente";
        }
    }

    @GetMapping("/mostrarEventos")
    public String mostrarEventos(Model model){
        model.addAttribute("evento", new EventoHotel());
        model.addAttribute("eventoAtualizar", new EventoHotel());
        return popularModel(model);
    }

    @PostMapping("/addEvento")
    public String adicionarEvento(@Valid @ModelAttribute("evento") EventoHotel evento,
                                  BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("eventoAtualizar", new EventoHotel());
            return popularModel(model);
        }

        try {
            eventoService.criarEvento(evento);
        }catch (HootelException e) {
            model.addAttribute("errorMessageAdd", e.getMessage());
            return popularModel(model);
        }

        return "redirect:/mostrarEventos";
    }

    @PostMapping("/removeEvento")
    public String removerEvento(@RequestParam(value = "id_evento", required = false) Integer id_evento,
                                 Model model){

        if (id_evento == null) {
            model.addAttribute("errorMessageRemove", "O ID do evento não deve ser vazio.");
            model.addAttribute("evento", new EventoHotel());
            model.addAttribute("eventoAtualizar", new EventoHotel());
            return popularModel(model);
        }

        try {
            Evento evento = eventoService.lerEventoId(id_evento);
            eventoService.deletarEvento(evento.getId());
        }catch (HootelException e) {
            model.addAttribute("errorMessageRemove", e.getMessage());
            model.addAttribute("evento", new EventoHotel());
            model.addAttribute("eventoAtualizar", new EventoHotel());
            return popularModel(model);
        }
        return "redirect:/mostrarEventos";
    }

    @PostMapping("/atualizaEvento")
    public String atualizarEvento(@Valid @ModelAttribute("eventoAtualizar") EventoHotel evento,
                                BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("evento", new EventoHotel());
            return popularModel(model);
        }

        if (evento.getId() == null) {
            model.addAttribute("errorMessageUpdate", "O ID do evento não deve ser vazio.");
            model.addAttribute("evento", new EventoHotel());
            return popularModel(model);
        }

        try {
            eventoService.atualizarEvento(evento);
        }catch (HootelException e) {
            model.addAttribute("errorMessageUpdate", e.getMessage());
            model.addAttribute("evento", new EventoHotel());
            return popularModel(model);
        }

        return "redirect:/mostrarEventos";
    }
}
