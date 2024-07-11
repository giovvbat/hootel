package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.EventoSpaResort;
import com.grupo11.hootel.entity.enums.ObjetivosSpaResort;
import com.grupo11.hootel.entity.enums.PreferenciaEventoSpaResort;
import com.grupo11.hootel.exceptions.HoospedagemException;
import com.grupo11.hootel.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/spa")
public class GerenteEventosSpaResortController {
    private EventoService eventoService;

    public GerenteEventosSpaResortController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    public String popularModel(Model model){
        try {
            List<Evento> eventos = eventoService.lerTodosEventos(EventoSpaResort.class);
            List<EventoSpaResort> eventoSpaResorts = new ArrayList<>();
            for (Evento evento : eventos){
                eventoSpaResorts.add((EventoSpaResort) evento);
            }
            model.addAttribute("eventos", eventoSpaResorts);
            model.addAttribute("categorias", PreferenciaEventoSpaResort.values());
            model.addAttribute("objetivos", ObjetivosSpaResort.values());
            return "spa/eventos_gerente";
        } catch (HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("eventos", new ArrayList<EventoSpaResort>());
            model.addAttribute("categorias", PreferenciaEventoSpaResort.values());
            model.addAttribute("objetivos", ObjetivosSpaResort.values());
            return "spa/add_evento_gerente";
        }
    }

    @GetMapping("/mostrarEventos")
    public String mostrarEventos(Model model){
        model.addAttribute("evento", new EventoSpaResort());
        model.addAttribute("eventoAtualizar", new EventoSpaResort());
        return popularModel(model);
    }

    @PostMapping("/addEvento")
    public String adicionarEvento(@Valid @ModelAttribute("evento") EventoSpaResort evento,
                                  BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("eventoAtualizar", new EventoSpaResort());
            return popularModel(model);
        }

        try {
            eventoService.criarEvento(evento);
        }catch (HoospedagemException e) {
            model.addAttribute("errorMessageAdd", e.getMessage());
            return popularModel(model);
        }

        return "redirect:/spa/mostrarEventos";
    }

    @PostMapping("/removeEvento")
    public String removerEvento(@RequestParam(value = "id_evento", required = false) Integer id_evento,
                                Model model){

        if (id_evento == null) {
            model.addAttribute("errorMessageRemove", "O ID do evento não deve ser vazio.");
            model.addAttribute("evento", new EventoSpaResort());
            model.addAttribute("eventoAtualizar", new EventoSpaResort());
            return popularModel(model);
        }

        try {
            Evento evento = eventoService.lerEventoId(id_evento);
            eventoService.deletarEvento(evento.getId());
        }catch (HoospedagemException e) {
            model.addAttribute("errorMessageRemove", e.getMessage());
            model.addAttribute("evento", new EventoSpaResort());
            model.addAttribute("eventoAtualizar", new EventoSpaResort());
            return popularModel(model);
        }
        return "redirect:/spa/mostrarEventos";
    }

    @PostMapping("/atualizaEvento")
    public String atualizarEvento(@Valid @ModelAttribute("eventoAtualizar") EventoSpaResort evento,
                                  BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("evento", new EventoSpaResort());
            return popularModel(model);
        }

        if (evento.getId() == null) {
            model.addAttribute("errorMessageUpdate", "O ID do evento não deve ser vazio.");
            model.addAttribute("evento", new EventoSpaResort());
            return popularModel(model);
        }

        try {
            eventoService.atualizarEvento(evento);
        }catch (HoospedagemException e) {
            model.addAttribute("errorMessageUpdate", e.getMessage());
            model.addAttribute("evento", new EventoSpaResort());
            return popularModel(model);
        }

        return "redirect:/spa/mostrarEventos";
    }
}
