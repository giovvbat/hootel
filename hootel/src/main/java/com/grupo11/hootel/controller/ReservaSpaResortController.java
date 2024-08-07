package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaSpaResort;
import com.grupo11.hootel.entity.enums.ObjetivosSpaResort;
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

@Controller
@RequestMapping("/spa")
public class ReservaSpaResortController {
    
    private ReservaService reservaService;

    public ReservaSpaResortController(ReservaService ReservaService) {
        this.reservaService = ReservaService;
    }

    @GetMapping("/index")
    public String mostrarInicial(){
        return "spa/index";
    }

    @GetMapping("/home")
    public String mostrarHome(){
        return "spa/home";
    }

    @GetMapping("/reserva/formulario")
    public String mostrarFormulario(Model theModel) {
        
        Reserva reserva = new ReservaSpaResort();

        theModel.addAttribute("reserva", reserva);

        return "spa/login";
    }

    @PostMapping("/reserva/checar")
    public String checarPIN(@Valid @ModelAttribute("reserva") ReservaSpaResort aReserva,
                            BindingResult bindingResult,
                            Model model) {

        if (bindingResult.hasErrors()) {
            return "spa/login";
        }

        try {
            ReservaSpaResort reserva = (ReservaSpaResort) reservaService.lerReservaPin(aReserva.getPIN());

            if (reserva.getObjetivos().isEmpty()) {
                model.addAttribute("reserva", reserva);
                model.addAttribute("objetivos", ObjetivosSpaResort.values());
                return "spa/preferencias";
            }
        } catch (HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "spa/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "PIN inválido");
            return "spa/login";
        }

        return "spa/home";
    }

    @PostMapping("/reserva/preferencias")
    public String checarPreferencias(@Valid @ModelAttribute("reserva") ReservaSpaResort aReserva,
                                     BindingResult bindingResult,
                                     Model model) {

        if (bindingResult.hasErrors()) {
            return "spa/preferencias";
        }

        try {
            reservaService.updateReserva(aReserva);
        } catch (HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "spa/preferencias";
        }

        return "spa/home";
    }
}
