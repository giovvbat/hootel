package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.AgendamentoServicoLavanderiaCruzeiro;
import com.grupo11.hootel.entity.ReservaCruzeiro;
import com.grupo11.hootel.entity.enums.EspecificacoesLavanderia;
import com.grupo11.hootel.entity.enums.HorarioAgendamento;
import com.grupo11.hootel.exceptions.HoospedagemException;
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
@RequestMapping("/cruzeiro")
public class AgendamentoLavanderiaCruzeiroController {

    private AgendamentoServicoService agendamentoService;

    public AgendamentoLavanderiaCruzeiroController(AgendamentoServicoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }


    @ModelAttribute("agendamentoLavanderia")
    public AgendamentoServicoLavanderiaCruzeiro carregarAgendamentoLavanderia() {
        AgendamentoServicoLavanderiaCruzeiro agendamento = new AgendamentoServicoLavanderiaCruzeiro();
        agendamento.setReserva(new ReservaCruzeiro());
        return agendamento;
    }

    @ModelAttribute("horarios")
    public HorarioAgendamento[] getHorarioAgendamentos() {
        return HorarioAgendamento.values();
    }

    @ModelAttribute("servicos")
    public EspecificacoesLavanderia[] getEspecificacoesLavanderia() {
        return EspecificacoesLavanderia.values();
    }

    @GetMapping("/lavanderia")
    public String mostrarCamareiras(Model model) {
        return "cruzeiro/lavanderia";
    }

    @PostMapping("/processarLavanderia")
    public String processarCamareiras(@Valid @ModelAttribute("agendamentoLavanderia") AgendamentoServicoLavanderiaCruzeiro agendamentoCamareira,
                                      BindingResult bindingResult,
                                      Model model

    ) {
        if (bindingResult.hasErrors()) {
            return "cruzeiro/lavanderia";
        }

        try {
            agendamentoService.criarAgendamento(agendamentoCamareira);
        } catch(HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "cruzeiro/lavanderia";
        }

        return "redirect:/cruzeiro/home";
    }
}