package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.HorarioCamareira;
import com.grupo11.hootel.service.HorarioCamareiraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/camareiras")
    public String mostrarCamareiras(Model model) {

        List<HorarioCamareira> horarios = camareiraService.getHorariosDisponiveis();
        HorarioCamareira horarioCamareira = new HorarioCamareira();

        model.addAttribute("horarios", horarios);

        model.addAttribute("horarioCamareira", horarioCamareira);

        return "camareiras";
    }

    @PostMapping("/processarCamareiras")
    public String processarCamareiras(@ModelAttribute("horarioCamareira") HorarioCamareira horarioCamareira) {
        camareiraService.atualizarHorario(
                horarioCamareira.getId(),
                horarioCamareira.getReserva().getPIN(),
                horarioCamareira.getServicos());

        return "redirect:home";
    }
}








