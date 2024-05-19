package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.HorarioCamareira;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.service.HorarioCamareiraService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CamareiraController {

    private HorarioCamareiraService camareiraService;

    public CamareiraController(HorarioCamareiraService camareiraService) {
        this.camareiraService = camareiraService;
    }

    @ModelAttribute("horarios")
    public List<HorarioCamareira> loadHorarios() {
        return camareiraService.getHorariosDisponiveis();
    }

    @ModelAttribute("horarioCamareira")
    public HorarioCamareira loadHorarioCamareira() {
        return new HorarioCamareira();
    }

    @GetMapping("/camareiras")
    public String mostrarCamareiras(Model model) {
        return "camareiras";
    }

    @PostMapping("/processarCamareiras")
    public String processarCamareiras(@Valid @ModelAttribute("horarioCamareira") HorarioCamareira horarioCamareira,
                                      BindingResult bindingResult,
                                      Model model

    ) {
        if (bindingResult.hasErrors()) {
            return "camareiras";
        }

        try {
            camareiraService.atualizarHorario(
                    horarioCamareira.getId(),
                    horarioCamareira.getReserva().getPIN(),
                    horarioCamareira.getServicos());
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "camareiras";
        }


        return "redirect:home";
    }
}








