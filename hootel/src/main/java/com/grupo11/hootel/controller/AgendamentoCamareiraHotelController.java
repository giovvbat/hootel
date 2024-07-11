package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.AgendamentoServicoCamareiraHotel;
import com.grupo11.hootel.entity.ReservaHotel;
import com.grupo11.hootel.entity.enums.EspecificacoesCamareira;
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
@RequestMapping("/hotel")
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

    @ModelAttribute("horarios")
    public HorarioAgendamento[] getHorariosAgendamento() {
        return HorarioAgendamento.values();
    }

    @ModelAttribute("servicos")
    public EspecificacoesCamareira[] getEspecificacoes() {
        return EspecificacoesCamareira.values();
    }

    @GetMapping("/camareiras")
    public String mostrarCamareiras(Model model) {
        return "hotel/camareiras";
    }

    @PostMapping("/processarCamareiras")
    public String processarCamareiras(@Valid @ModelAttribute("agendamentoCamareira") AgendamentoServicoCamareiraHotel agendamentoCamareira,
                                      BindingResult bindingResult,
                                      Model model

    ) {
        if (bindingResult.hasErrors()) {
            return "hotel/camareiras";
        }

        try {
            agendamentoService.criarAgendamento(agendamentoCamareira);
        } catch(HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "hotel/camareiras";
        }

        return "redirect:/hotel/home";
    }
}








