package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventoController {
    private EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/eventos")
    public String mostrarEventos(Model model) {
        List<Evento> eventos = eventoService.lerTodosEventos();
        model.addAttribute("eventos", eventos);
        return "eventos_rascunho";
    }

    @GetMapping("/evento")
    public String mostrarEventoPorId(@RequestParam("eventoId") int theId,
                              Model model) {
        try {
            Evento evento = eventoService.lerEventoId(theId);
            model.addAttribute("evento_escolhido", evento);
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "teste";
        }
        return "eventoEspecifico_rascunho";
    }

    @PostMapping("/evento/participacao/add")
    public String cadastrarParticipacao(@ModelAttribute("evento_escolhido") Evento evento,
                                        @RequestParam("pin") Long pin,
                                        Model model){

        try {
            eventoService.adicionarParticipante(pin, evento.getId());
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/eventos";
    }

    @PostMapping("/evento/participacao/rm")
    public String removerParticipacao(@ModelAttribute("evento_escolhido") Evento evento,
                                        @RequestParam("pin") Long pin,
                                        Model model){

        try {
            eventoService.removerParticipante(pin, evento.getId());
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/eventos";
    }

}
