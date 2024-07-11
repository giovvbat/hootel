package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaHotel;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarHotel;
import com.grupo11.hootel.entity.enums.PreferenciaEventoHotel;
import com.grupo11.hootel.exceptions.HoospedagemException;
import com.grupo11.hootel.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hotel")
public class ReservaHotelController {

    private ReservaService reservaService;

    public ReservaHotelController(ReservaService ReservaService) {
        this.reservaService = ReservaService;
    }

    @GetMapping("/index")
    public String mostrarInicial(){
        return "hotel/index";
    }

    @GetMapping("/home")
    public String mostrarHome(){
        return "hotel/home";
    }

    @GetMapping("/reserva/formulario")
    public String mostrarFormulario(Model theModel) {

        Reserva reserva = new ReservaHotel();

        theModel.addAttribute("reserva", reserva);

        return "hotel/login";
    }

    @PostMapping("/reserva/checar")
    public String checarPIN(@Valid @ModelAttribute("reserva") ReservaHotel aReserva,
                            BindingResult bindingResult,
                            Model model) {

        if (bindingResult.hasErrors()) {
            return "hotel/login";
        }

        try {
            ReservaHotel reserva = (ReservaHotel) reservaService.lerReservaPin(aReserva.getPIN());

            if (reserva.getPreferenciasEventos().isEmpty() || reserva.getPreferenciasAlimentares().isEmpty()) {
                model.addAttribute("reserva", reserva);
                model.addAttribute("tiposPrefAlimentar", PreferenciaAlimentarHotel.values());
                model.addAttribute("tiposPrefEvento", PreferenciaEventoHotel.values());
                return "hotel/preferencias";
            }
        } catch (HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "hotel/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "PIN inválido");
            return "spa/login";
        }

        return "hotel/home";
    }

    @PostMapping("/reserva/preferencias")
    public String checarPreferencias(@Valid @ModelAttribute("reserva") ReservaHotel aReserva,
                                     BindingResult bindingResult,
                                     Model model) {

        if (bindingResult.hasErrors()) {
            return "hotel/preferencias";
        }

        try {
            reservaService.updateReserva(aReserva);
        } catch (HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "hotel/preferencias";
        }

        return "hotel/home";
    }
}
