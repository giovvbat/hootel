package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaCruzeiro;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarCruzeiro;
import com.grupo11.hootel.entity.enums.PreferenciaEventoCruzeiro;
import com.grupo11.hootel.entity.enums.TurnoEventoCruzeiro;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cruzeiro")
public class ReservaCruzeiroController {
    private ReservaService reservaService;

    public ReservaCruzeiroController(ReservaService ReservaService) {
        this.reservaService = ReservaService;
    }

    @GetMapping("/index")
    public String mostrarInicial(){
        return "cruzeiro/index";
    }

    @GetMapping("/home")
    public String mostrarHome(){
        return "cruzeiro/home";
    }

    @GetMapping("/reserva/formulario")
    public String mostrarFormulario(Model theModel) {

        Reserva reserva = new ReservaCruzeiro();

        theModel.addAttribute("reserva", reserva);

        return "cruzeiro/login";
    }

    @PostMapping("/reserva/checar")
    public String checarPIN(@Valid @ModelAttribute("reserva") ReservaCruzeiro aReserva,
                            BindingResult bindingResult,
                            Model model) {

        if (bindingResult.hasErrors()) {
            return "cruzeiro/login";
        }

        try {
            ReservaCruzeiro reserva = (ReservaCruzeiro) reservaService.lerReservaPin(aReserva.getPIN());

            if (reserva.getPreferenciasEventos().isEmpty() || reserva.getPreferenciasAlimentares().isEmpty()) {
                model.addAttribute("reserva", reserva);
                model.addAttribute("tiposPrefAlimentar", PreferenciaAlimentarCruzeiro.values());
                model.addAttribute("tiposPrefEvento", PreferenciaEventoCruzeiro.values());
                model.addAttribute("turnos", TurnoEventoCruzeiro.values());
                return "cruzeiro/preferencias";
            }
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "cruzeiro/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "PIN inválido");
            return "cruzeiro/login";
        }

        return "cruzeiro/home";
    }

    @PostMapping("/reserva/preferencias")
    public String checarPreferencias(@Valid @ModelAttribute("reserva") ReservaCruzeiro aReserva,
                                     BindingResult bindingResult,
                                     Model model) {

        if (bindingResult.hasErrors()) {
            return "cruzeiro/preferencias";
        }

        try {
            reservaService.updateReserva(aReserva);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "cruzeiro/preferencias";
        }

        return "cruzeiro/home";
    }
}
