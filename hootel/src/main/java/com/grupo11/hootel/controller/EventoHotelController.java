package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.EventoHotel;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaHotel;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventoHotelController {
    private EventoService eventoService;
    private ReservaService reservaService;
    private RecomendacaoEventosService recomendacaoService;

    public EventoHotelController(EventoService eventoService, ReservaService reservaService, RecomendacaoEventosService recomendacaoService) {
        this.eventoService = eventoService;
        this.reservaService = reservaService;
        this.recomendacaoService = recomendacaoService;
    }

    @GetMapping("/eventos")
    public String mostrarEventos(Model model) {
        try {
            List<Evento> eventos = eventoService.lerTodosEventos(EventoHotel.class);
            model.addAttribute("eventos", eventos);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("eventos", new ArrayList<>());
        }
        return "eventos";
    }

    @GetMapping("/evento")
    public String mostrarEventoPorId(@RequestParam("eventoId") int theId,
                              Model model) {
        try {
            Evento evento = eventoService.lerEventoId(theId);
            ReservaHotel reserva = new ReservaHotel();
            model.addAttribute("evento_escolhido", evento);
            model.addAttribute("reserva", reserva);
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("evento_escolhido", new EventoHotel());
            model.addAttribute("reserva", new ReservaHotel());
        }
        return "eventoEspecifico";
    }

    @PostMapping("/evento/participacao/add")
    public String cadastrarParticipacao(@ModelAttribute("evento_escolhido") Evento evento,
                                        @Valid @ModelAttribute("reserva") Reserva reserva,
                                        BindingResult bindingResultReserva, Model model){

        if (bindingResultReserva.hasErrors() ) {
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "eventoEspecifico";
        }

        try {
            eventoService.adicionarParticipante(reserva.getPIN(), evento.getId());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "eventoEspecifico";
        }

        return "redirect:/eventos";
    }

    @PostMapping("/evento/participacao/rm")
    public String removerParticipacao(@ModelAttribute("evento_escolhido") Evento evento,
                                      @Valid @ModelAttribute("reserva") Reserva reserva,
                                      BindingResult bindingResultReserva, Model model){

        if (bindingResultReserva.hasErrors()) {
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "eventoEspecifico";
        }

        try {
            eventoService.removerParticipante(reserva.getPIN(), evento.getId());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "eventoEspecifico";
        }

        return "redirect:/eventos";
    }

    @PostMapping("/evento/recomendacao")
    public String recomendarEventos(@Valid @ModelAttribute("reserva") Reserva aReserva,
                                    BindingResult bindingResult,
                                    Model model) {

        if(bindingResult.hasErrors()) {
            List<Evento> eventos = eventoService.lerTodosEventos(EventoHotel.class);
            model.addAttribute("eventos", eventos);
            return "eventos";
        }

        try {
            Reserva reserva = reservaService.lerReservaPin(aReserva.getPIN());
            EstrategiaRecomendacaoEventos estrategia = new HotelEstrategiaRecomendacaoEventos();
            List<Evento> eventos = eventoService.lerTodosEventos(EventoHotel.class);

            List<Evento> recomendacao = recomendacaoService.recomendarEventos(estrategia, eventos, reserva);

            model.addAttribute("recomendacao", recomendacao);
            model.addAttribute("eventos", eventos);

            return "eventos";
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "eventos";
        }
    }
}
