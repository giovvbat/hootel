package com.grupo11.hootel.rest;

import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservaRestController {

    private ReservaService reservaService;

    @Autowired
    public ReservaRestController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/reservas")
    public List<Reserva> lerTodasReservas() {
        return reservaService.lerTodasReservas();
    }

    @GetMapping("/reservas/{pin}")
    public Reserva lerReservaPin(@PathVariable long pin) {
        return reservaService.lerReservaPin(pin);
    }

    @DeleteMapping("/reservas/{pin}")
    public void deletarReserva(@PathVariable long pin) {
        Reserva reserva = reservaService.lerReservaPin(pin);

        if (reserva == null) {
            return;
        }

        reservaService.deletarReserva(reserva);
    }

}
