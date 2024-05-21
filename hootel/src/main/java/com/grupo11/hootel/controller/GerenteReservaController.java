package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GerenteReservaController {

    private ReservaService reservaService;

    public GerenteReservaController(ReservaService ReservaService) {
        this.reservaService = ReservaService;
    }

    @GetMapping("/mostrarReservas")
    public String mostrarReservas(Model model){

        List<Reserva> todasReservas = reservaService.lerTodasReservas();
        model.addAttribute("todasReservas", todasReservas);
        //model.addAttribute("reserva", new Reserva());

        return "reservas";
    }

    @PostMapping("/addReserva")
    public String criarNovaReserva(@Valid @ModelAttribute("reserva") Reserva reserva,
                                   Model model) {

        reserva = reservaService.criarReserva();
        model.addAttribute("novaReserva", reserva);

        return "reservas";
    }

    @PostMapping("/removeReserva")
    public String removeReserva(@Valid @ModelAttribute("reserva") Reserva reserva,
                                BindingResult bindingResult,
                                Model model){

        if (bindingResult.hasErrors()){
            return "reservas";
        }

        try {
            reservaService.deletarReserva(reserva);
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "reservas";
    }
}
