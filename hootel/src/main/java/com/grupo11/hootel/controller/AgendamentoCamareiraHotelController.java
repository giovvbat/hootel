package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.AgendamentoServicoCamareiraHotel;
import com.grupo11.hootel.entity.ReservaHotel;
import com.grupo11.hootel.entity.enums.EspecificacoesCamareira;
import com.grupo11.hootel.entity.enums.HorarioAgendamento;
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
        AgendamentoServicoCamareiraHotel agendamento = new AgendamentoServicoCamareiraHotel();
        agendamento.setReserva(new ReservaHotel());
        return agendamento;
    }

    @GetMapping("/camareiras")
    public String mostrarCamareiras(Model model) {
        model.addAttribute("horarios", HorarioAgendamento.values());
        model.addAttribute("servicos", EspecificacoesCamareira.values());
        return "camareiras";
    }

    @PostMapping("/processarCamareiras")
    public String processarCamareiras(@Valid @ModelAttribute("agendamentoCamareira") AgendamentoServicoCamareiraHotel agendamentoCamareira,
                                      BindingResult bindingResult,
                                      Model model

    ) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "camareiras";
        }

        try {
            agendamentoService.criarAgendamento(agendamentoCamareira);
        } catch(HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            System.out.println(e.getMessage());
            return "camareiras";
        }

        return "redirect:home";
    }
}








