package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.EventoSpaResort;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaSpaResort;
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
@RequestMapping("/spa")
public class EventoSpaResortController {
    private EventoService eventoService;
    private ReservaService reservaService;
    private RecomendacaoEventosService recomendacaoService;
    private SpaResortEstrategiaRecomendacaoEventos estrategia;

    public EventoSpaResortController(EventoService eventoService, ReservaService reservaService, RecomendacaoEventosService recomendacaoService) {
        this.eventoService = eventoService;
        this.reservaService = reservaService;
        this.recomendacaoService = recomendacaoService;
        this.estrategia = new SpaResortEstrategiaRecomendacaoEventos();
    }

    @GetMapping("/eventos")
    public String mostrarEventos(Model model) {
        try {
            List<Evento> eventos = eventoService.lerTodosEventos(EventoSpaResort.class);
            model.addAttribute("eventos", eventos);
            model.addAttribute("reserva", new ReservaSpaResort());
            model.addAttribute("recomendacao", new ArrayList<Evento>());

        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("eventos", new ArrayList<Evento>());
            model.addAttribute("recomendacao", new ArrayList<Evento>());
            model.addAttribute("reserva", new ReservaSpaResort());
        }
        return "spa/eventos";
    }

    @GetMapping("/evento")
    public String mostrarEventoPorId(@RequestParam("eventoId") int theId,
                                     Model model) {
        try {
            EventoSpaResort evento = (EventoSpaResort) eventoService.lerEventoId(theId);
            ReservaSpaResort reserva = new ReservaSpaResort();
            model.addAttribute("evento_escolhido", evento);
            model.addAttribute("reserva", reserva);
        } catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("evento_escolhido", new EventoSpaResort());
            model.addAttribute("reserva", new ReservaSpaResort());
        }
        return "spa/eventoEspecifico";
    }

    @PostMapping("/evento/participacao/add")
    public String cadastrarParticipacao(@ModelAttribute("evento_escolhido") EventoSpaResort evento,
                                        @Valid @ModelAttribute("reserva") ReservaSpaResort reserva,
                                        BindingResult bindingResultReserva, Model model){

        if (bindingResultReserva.hasErrors() ) {
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "spa/eventoEspecifico";
        }

        try {
            eventoService.adicionarParticipante(reserva.getPIN(), evento.getId());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "spa/eventoEspecifico";
        }

        return "redirect:/spa/eventos";
    }

    @PostMapping("/evento/participacao/rm")
    public String removerParticipacao(@ModelAttribute("evento_escolhido") EventoSpaResort evento,
                                      @Valid @ModelAttribute("reserva") ReservaSpaResort reserva,
                                      BindingResult bindingResultReserva, Model model){

        if (bindingResultReserva.hasErrors()) {
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "spa/eventoEspecifico";
        }

        try {
            eventoService.removerParticipante(reserva.getPIN(), evento.getId());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "spa/eventoEspecifico";
        }

        return "redirect:/spa/eventos";
    }

    @GetMapping("/evento/recomendacao")
    public String recomendarEventos(@Valid @ModelAttribute("reserva") ReservaSpaResort aReserva,
                                    BindingResult bindingResult,
                                    Model model) {

        if(bindingResult.hasErrors()) {
            List<Evento> eventos = eventoService.lerTodosEventos(EventoSpaResort.class);
            model.addAttribute("eventos", eventos);
            return "spa/eventos";
        }

        try {
            Reserva reserva = reservaService.lerReservaPin(aReserva.getPIN());
            List<Evento> eventos = eventoService.lerTodosEventos(EventoSpaResort.class);

            List<Evento> recomendacao = recomendacaoService.recomendarEventos(estrategia, eventos, reserva);

            model.addAttribute("recomendacao", recomendacao);
            model.addAttribute("eventos", eventos);

            return "spa/eventos";
        } catch (HootelException e) {
            model.addAttribute("recomendacao", new ArrayList<>());
            model.addAttribute("errorMessage", e.getMessage());
            try {
                model.addAttribute("eventos", eventoService.lerTodosEventos(EventoSpaResort.class));
            } catch (HootelException e2) {
                model.addAttribute("eventos", new ArrayList<>());
            }
            return "spa/eventos";
        }
    }
}
