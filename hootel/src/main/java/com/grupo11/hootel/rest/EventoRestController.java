package com.grupo11.hootel.rest;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventoRestController {

    private final EventoService eventoService;

    @Autowired
    public EventoRestController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/eventos")
    public List<Evento> lerTodosEventos() {
        return eventoService.lerTodosEventos();
    }

    @GetMapping("/eventos/{id}")
    public Evento lerEventoId(@PathVariable int id) {
        return eventoService.lerEventoId(id);
    }

    @PostMapping("/eventos")
    public void adicionarEvento(@RequestBody Evento evento) {
        eventoService.criarEvento(evento);
    }

    @PutMapping("/eventos/{id}")
    public void atualizarEvento(@RequestBody Evento evento, @PathVariable int id) {
        Evento eventoBD = eventoService.lerEventoId(id);

        if (eventoBD == null) {
            return;
        }

        evento.setId(id);
        eventoService.atualizarEvento(evento);
    }

}
