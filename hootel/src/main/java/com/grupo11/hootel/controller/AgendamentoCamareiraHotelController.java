package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.AgendamentoServicoCamareiraHotel;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.AgendamentoServicoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgendamentoCamareiraHotelController {

    private AgendamentoServicoService agendamentoService;

    public AgendamentoCamareiraHotelController(AgendamentoServicoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }


    @ModelAttribute("agendamentoCamareira")
    public AgendamentoServicoCamareiraHotel carregarAgendamentoCamareira() {
        return new AgendamentoServicoCamareiraHotel();
    }

    @GetMapping("/camareiras")
    public String mostrarCamareiras(Model model) {
        return "camareiras";
    }

    @PostMapping("/processarCamareiras")
    public String processarCamareiras(@Valid @ModelAttribute("agendamentoCamareira") AgendamentoServicoCamareiraHotel agendamentoCamareira,
                                      BindingResult bindingResult,
                                      Model model

    ) {
        if (bindingResult.hasErrors()) {
            return "camareiras";
        }

        try {
            agendamentoService.criarAgendamento(agendamentoCamareira);
        } catch(HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "camareiras";
        }


        return "redirect:home";
    }
}








