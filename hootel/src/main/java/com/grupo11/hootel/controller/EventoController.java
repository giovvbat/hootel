package com.grupo11.hootel.controller;

import com.grupo11.hootel.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventoController {
    private EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/eventos")
    public String getEventos() {
        return "eventos";
    }
}
