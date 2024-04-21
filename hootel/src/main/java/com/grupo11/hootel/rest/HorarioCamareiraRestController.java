package com.grupo11.hootel.rest;

import com.grupo11.hootel.entity.HorarioCamareira;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.service.HorarioCamareiraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HorarioCamareiraRestController {

    private final HorarioCamareiraService horarioCamareiraService;

    public HorarioCamareiraRestController(HorarioCamareiraService horarioCamareiraService) {
        this.horarioCamareiraService = horarioCamareiraService;
    }

    @GetMapping("/camareiras")
    public List<HorarioCamareira> getHorariosCamareiras() {
        return horarioCamareiraService.getHorariosCamareiras();
    }

    @GetMapping("/camareiras/{pin}")
    public List<HorarioCamareira> getHorariosPin(@PathVariable int pin) {
        return horarioCamareiraService.getHorariosPin(pin);
    }

    @GetMapping("/camareiras/disponivel")
    public List<HorarioCamareira> getHorariosDisponiveis() {
        return horarioCamareiraService.getHorariosDisponiveis();
    }


    @PutMapping("/camareiras/{id}")
    public HorarioCamareira atribuirHorario(@PathVariable int id, @RequestBody long pin) {
        return horarioCamareiraService.atualizarHorario(id, pin);
    }
}
