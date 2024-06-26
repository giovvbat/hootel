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

import java.util.ArrayList;

@Controller
public class ReservaController {

    private ReservaService reservaService;

    public ReservaController(ReservaService ReservaService) {
        this.reservaService = ReservaService;
    }

    @GetMapping("/index")
    public String mostrarInicial(){
        return "index";
    }

    @GetMapping("/home")
    public String mostrarHome(){
        return "home";
    }

    @GetMapping("/reserva/formulario")
    public String mostrarFormulario(Model theModel) {

        Reserva reserva = new Reserva();

        theModel.addAttribute("reserva", reserva);

        return "login";
    }

    @PostMapping("/reserva/checar")
    public String checarPIN(@Valid @ModelAttribute("reserva") Reserva aReserva,
                            BindingResult bindingResult,
                            Model model) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            Reserva reserva = reservaService.lerReservaPin(aReserva.getPIN());

            if (reserva.getPreferenciasAlimentacao() == null || reserva.getPreferenciasEventos() == null) {
                model.addAttribute("reserva", reserva);
                return "preferencias";
            }
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }

        return "home";
    }

    @PostMapping("/reserva/preferencias")
    public String checarPreferencias(@Valid @ModelAttribute("reserva") Reserva aReserva,
                                     BindingResult bindingResult,
                                     Model model) {

        if (bindingResult.hasErrors()) {
            return "preferencias";
        }

        try {
            reservaService.lerReservaPin(aReserva.getPIN());
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "preferencias";
        }

        return "home";
    }
}
