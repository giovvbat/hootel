package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.*;
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
@RequestMapping("/hotel")
public class EventoHotelController {
    private EventoService eventoService;
    private ReservaService reservaService;
    private RecomendacaoEventosService recomendacaoService;
    private HotelEstrategiaRecomendacaoEventos estrategia;

    public EventoHotelController(EventoService eventoService, ReservaService reservaService, RecomendacaoEventosService recomendacaoService) {
        this.eventoService = eventoService;
        this.reservaService = reservaService;
        this.recomendacaoService = recomendacaoService;
        this.estrategia = new HotelEstrategiaRecomendacaoEventos();
    }

    @GetMapping("/eventos")
    public String mostrarEventos(Model model) {
        try {
            List<Evento> eventos = eventoService.lerTodosEventos(EventoHotel.class);
            model.addAttribute("eventos", eventos);
            model.addAttribute("reserva", new ReservaHotel());
            model.addAttribute("recomendacao", new ArrayList<Evento>());
            System.out.println("entrou aqui 01");

        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("eventos", new ArrayList<Evento>());
            model.addAttribute("recomendacao", new ArrayList<Evento>());
            model.addAttribute("reserva", new ReservaHotel());
            System.out.println("entrou aqui 02");
        }
        return "hotel/eventos";
    }

    @GetMapping("/evento")
    public String mostrarEventoPorId(@RequestParam("eventoId") int theId,
                              Model model) {
        try {
            Evento evento = eventoService.lerEventoId(theId);
            ReservaHotel reserva = new ReservaHotel();
            model.addAttribute("evento_escolhido", evento);
            model.addAttribute("reserva", reserva);
        } catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("evento_escolhido", new EventoHotel());
            model.addAttribute("reserva", new ReservaHotel());
        }
        return "hotel/eventoEspecifico";
    }

    @PostMapping("/evento/participacao/add")
    public String cadastrarParticipacao(@ModelAttribute("evento_escolhido") EventoHotel evento,
                                        @Valid @ModelAttribute("reserva") ReservaHotel reserva,
                                        BindingResult bindingResultReserva, Model model){

        if (bindingResultReserva.hasErrors() ) {
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "hotel/eventoEspecifico";
        }

        try {
            eventoService.adicionarParticipante(reserva.getPIN(), evento.getId());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "hotel/eventoEspecifico";
        }

        return "redirect:/hotel/eventos";
    }

    @PostMapping("/evento/participacao/rm")
    public String removerParticipacao(@ModelAttribute("evento_escolhido") EventoHotel evento,
                                      @Valid @ModelAttribute("reserva") ReservaHotel reserva,
                                      BindingResult bindingResultReserva, Model model){

        if (bindingResultReserva.hasErrors()) {
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "hotel/eventoEspecifico";
        }

        try {
            eventoService.removerParticipante(reserva.getPIN(), evento.getId());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "hotel/eventoEspecifico";
        }

        return "redirect:/hotel/eventos";
    }

    @GetMapping("/evento/recomendacao")
    public String recomendarEventos(@Valid @ModelAttribute("reserva") ReservaHotel aReserva,
                                    BindingResult bindingResult,
                                    Model model) {

        if(bindingResult.hasErrors()) {
            List<Evento> eventos = eventoService.lerTodosEventos(EventoHotel.class);
            model.addAttribute("eventos", eventos);
            return "hotel/eventos";
        }

        try {
            Reserva reserva = reservaService.lerReservaPin(aReserva.getPIN());
            List<Evento> eventos = eventoService.lerTodosEventos(EventoHotel.class);

            List<Evento> recomendacao = recomendacaoService.recomendarEventos(estrategia, eventos, reserva);

            model.addAttribute("recomendacao", recomendacao);
            model.addAttribute("eventos", eventos);

            return "hotel/eventos";
        } catch (HootelException e) {
            model.addAttribute("recomendacao", new ArrayList<>());
            model.addAttribute("errorMessage", e.getMessage());
            try {
                model.addAttribute("eventos", eventoService.lerTodosEventos(EventoHotel.class));
            } catch (HootelException e2) {
                model.addAttribute("eventos", new ArrayList<>());
            }
            return "hotel/eventos";
        }
    }
}
