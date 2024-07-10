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
@RequestMapping("/cruzeiro")
public class EventoCruzeiroController {
    private EventoService eventoService;
    private ReservaService reservaService;
    private RecomendacaoEventosService recomendacaoService;
    private CruzeiroEstrategiaRecomendacaoEventos estrategia;

    public EventoCruzeiroController(EventoService eventoService, ReservaService reservaService, RecomendacaoEventosService recomendacaoService) {
        this.eventoService = eventoService;
        this.reservaService = reservaService;
        this.recomendacaoService = recomendacaoService;
        this.estrategia = new CruzeiroEstrategiaRecomendacaoEventos();
    }

    @GetMapping("/eventos")
    public String mostrarEventos(Model model) {
        try {
            List<Evento> eventos = eventoService.lerTodosEventos(EventoCruzeiro.class);
            model.addAttribute("eventos", eventos);
            model.addAttribute("reserva", new ReservaCruzeiro());
            model.addAttribute("recomendacao", new ArrayList<Evento>());

        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("eventos", new ArrayList<Evento>());
            model.addAttribute("recomendacao", new ArrayList<Evento>());
            model.addAttribute("reserva", new ReservaCruzeiro());
        }
        return "cruzeiro/eventos";
    }

    @GetMapping("/evento")
    public String mostrarEventoPorId(@RequestParam("eventoId") int theId,
                                     Model model) {
        try {
            Evento evento = eventoService.lerEventoId(theId);
            ReservaCruzeiro reserva = new ReservaCruzeiro();
            model.addAttribute("evento_escolhido", evento);
            model.addAttribute("reserva", reserva);
        } catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("evento_escolhido", new EventoCruzeiro());
            model.addAttribute("reserva", new ReservaCruzeiro());
        }
        return "cruzeiro/eventoEspecifico";
    }

    @PostMapping("/evento/participacao/add")
    public String cadastrarParticipacao(@ModelAttribute("evento_escolhido") EventoCruzeiro evento,
                                        @Valid @ModelAttribute("reserva") ReservaCruzeiro reserva,
                                        BindingResult bindingResultReserva, Model model){

        if (bindingResultReserva.hasErrors() ) {
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "cruzeiro/eventoEspecifico";
        }

        try {
            eventoService.adicionarParticipante(reserva.getPIN(), evento.getId());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "cruzeiro/eventoEspecifico";
        }

        return "redirect:/cruzeiro/eventos";
    }

    @PostMapping("/evento/participacao/rm")
    public String removerParticipacao(@ModelAttribute("evento_escolhido") EventoCruzeiro evento,
                                      @Valid @ModelAttribute("reserva") ReservaCruzeiro reserva,
                                      BindingResult bindingResultReserva, Model model){

        if (bindingResultReserva.hasErrors()) {
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "cruzeiro/eventoEspecifico";
        }

        try {
            eventoService.removerParticipante(reserva.getPIN(), evento.getId());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            Evento eventoAtual = eventoService.lerEventoId(evento.getId());
            model.addAttribute("evento_escolhido", eventoAtual);
            return "cruzeiro/eventoEspecifico";
        }

        return "redirect:/cruzeiro/eventos";
    }

    @GetMapping("/evento/recomendacao")
    public String recomendarEventos(@Valid @ModelAttribute("reserva") ReservaCruzeiro aReserva,
                                    BindingResult bindingResult,
                                    Model model) {

        if(bindingResult.hasErrors()) {
            List<Evento> eventos = eventoService.lerTodosEventos(EventoCruzeiro.class);
            model.addAttribute("eventos", eventos);
            return "cruzeiro/eventos";
        }

        try {
            Reserva reserva = reservaService.lerReservaPin(aReserva.getPIN());
            List<Evento> eventos = eventoService.lerTodosEventos(EventoCruzeiro.class);

            List<Evento> recomendacao = recomendacaoService.recomendarEventos(estrategia, eventos, reserva);

            model.addAttribute("recomendacao", recomendacao);
            model.addAttribute("eventos", eventos);

            return "cruzeiro/eventos";
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("eventos", eventoService.lerTodosEventos(EventoCruzeiro.class));
            return "cruzeiro/eventos";
        }
    }
}
