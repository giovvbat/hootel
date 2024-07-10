package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.AgendamentoServicoRelaxamentoSpaResort;
import com.grupo11.hootel.entity.ReservaSpaResort;
import com.grupo11.hootel.entity.enums.EspecificacoesRelaxamento;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spa")
public class AgendamentoRelaxamentoSpaResortController {

    private AgendamentoServicoService agendamentoService;

    public AgendamentoRelaxamentoSpaResortController(AgendamentoServicoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }


    @ModelAttribute("agendamentoRelaxamento")
    public AgendamentoServicoRelaxamentoSpaResort carregarAgendamentoRelaxamento() {
        AgendamentoServicoRelaxamentoSpaResort agendamento = new AgendamentoServicoRelaxamentoSpaResort();
        agendamento.setReserva(new ReservaSpaResort());
        return agendamento;
    }

    @GetMapping("/relaxamento")
    public String mostrarRelaxamento(Model model) {
        model.addAttribute("horarios", HorarioAgendamento.values());
        model.addAttribute("servicos", EspecificacoesRelaxamento.values());
        return "spa/relaxamento";
    }

    @PostMapping("/processarRelaxamento")
    public String processarRelaxamento(@Valid @ModelAttribute("agendamentoRelaxamento") AgendamentoServicoRelaxamentoSpaResort agendamentoRelaxamento,
                                      BindingResult bindingResult,
                                      Model model

    ) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "spa/relaxamento";
        }

        try {
            agendamentoService.criarAgendamento(agendamentoRelaxamento);
        } catch(HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            System.out.println(e.getMessage());
            return "spa/relaxamento";
        }

        return "redirect:/spa/home";
    }
}
