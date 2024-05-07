package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Cardapio;
import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EventoController {
    private EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/eventos")
    public String getEventos(Model model) {
        try {
            List<Evento> eventos = eventoService.lerTodosEventos();
            model.addAttribute("eventos", eventos);
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "cardapio-error";
        }
        return "eventos";
    }

    @GetMapping("/evento")
    public String getEventoID(@RequestParam("eventoId") int theId,
                              Model model) {
        try {
            Evento evento = eventoService.lerEventoId(theId);
            model.addAttribute("evento_escolhido", evento);
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "evento_especifico";
    }

}
