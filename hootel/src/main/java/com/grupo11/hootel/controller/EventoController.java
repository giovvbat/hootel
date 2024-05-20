package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        return "eventos";
    }

    @GetMapping("/evento")
    public String mostrarEventoPorId(@RequestParam("eventoId") int theId,
                              Model model) {
        try {
            Evento evento = eventoService.lerEventoId(theId);
            Reserva reserva = new Reserva();
            model.addAttribute("evento_escolhido", evento);
            model.addAttribute("reserva", reserva);
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "teste";
        }
        return "eventoEspecifico";
    }

    @PostMapping("/evento/participacao/add")
    public String cadastrarParticipacao(@Valid @ModelAttribute("evento_escolhido") Evento evento,
                                        @Valid @ModelAttribute("reserva") Reserva reserva,
                                        BindingResult bindingResultReserva, Model model){

        if (bindingResultReserva.hasErrors() ) {
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "eventoEspecifico";
        }

        try {
            eventoService.adicionarParticipante(reserva.getPIN(), evento.getId());
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "eventoEspecifico";
        }

        return "redirect:/eventos";
    }

    @PostMapping("/evento/participacao/rm")
    public String removerParticipacao(@Valid @ModelAttribute("evento_escolhido") Evento evento,
                                      @Valid @ModelAttribute("reserva") Reserva reserva,
                                      BindingResult bindingResultReserva, Model model){

        if (bindingResultReserva.hasErrors()) {
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "eventoEspecifico";
        }

        try {
            eventoService.removerParticipante(reserva.getPIN(), evento.getId());
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "eventoEspecifico";
        }

        return "redirect:/eventos";
    }

}
