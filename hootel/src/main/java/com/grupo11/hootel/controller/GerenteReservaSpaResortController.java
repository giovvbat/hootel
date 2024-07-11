package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaSpaResort;
import com.grupo11.hootel.exceptions.HoospedagemException;
import com.grupo11.hootel.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/spa")
public class GerenteReservaSpaResortController {
    private ReservaService reservaService;

    public GerenteReservaSpaResortController(ReservaService ReservaService) {
        this.reservaService = ReservaService;
    }

    @ModelAttribute("todasReservas")
    public List<Reserva> populateReservas(Model model) {
        try {
            return reservaService.lerTodasReservas(ReservaSpaResort.class);
        } catch (HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return new ArrayList<>();
        }
    }

    @GetMapping("/mostrarReservas")
    public String mostrarReservas(Model model){
        model.addAttribute("reserva", new ReservaSpaResort());

        return "spa/reservas_gerente";
    }

    @PostMapping("/addReserva")
    public String criarNovaReserva(Model model) {

        Reserva reserva = reservaService.criarReserva(new ReservaSpaResort());
        model.addAttribute("novaReserva", reserva);

        return "redirect:/spa/mostrarReservas";
    }

    @PostMapping("/removeReserva")
    public String removeReserva(@Valid @ModelAttribute("reserva") Reserva reserva,
                                BindingResult bindingResult,
                                Model model){

        if (bindingResult.hasErrors()){
            return "spa/reservas_gerente";
        }

        try {
            reservaService.deletarReserva(reserva);
        }catch (HoospedagemException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "spa/reservas_gerente";
        }

        return "redirect:/spa/mostrarReservas";
    }
}
