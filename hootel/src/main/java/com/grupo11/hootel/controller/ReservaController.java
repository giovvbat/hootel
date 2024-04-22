package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.service.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String checarPIN(@ModelAttribute("reserva") Reserva aReserva) {

        if (reservaService.lerReservaPin(aReserva.getPIN()) != null) {
            return "home";
        }else {
            return "login";
        }
    }
}
