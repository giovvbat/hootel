package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.AgendamentoServicoMedicoCruzeiro;
import com.grupo11.hootel.entity.ReservaCruzeiro;
import com.grupo11.hootel.entity.enums.EspecificacoesMedico;
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
@RequestMapping("/cruzeiro")
public class AgendamentoMedicoCruzeiroController {

    private AgendamentoServicoService agendamentoService;

    public AgendamentoMedicoCruzeiroController(AgendamentoServicoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }


    @ModelAttribute("agendamentoMedico")
    public AgendamentoServicoMedicoCruzeiro carregarAgendamentoLavanderia() {
        AgendamentoServicoMedicoCruzeiro agendamento = new AgendamentoServicoMedicoCruzeiro();
        agendamento.setReserva(new ReservaCruzeiro());
        return agendamento;
    }

    @GetMapping("/medico")
    public String mostrarCamareiras(Model model) {
        model.addAttribute("horarios", HorarioAgendamento.values());
        model.addAttribute("servicos", EspecificacoesMedico.values());
        return "cruzeiro/medico";
    }

    @PostMapping("/processarLavanderia")
    public String processarCamareiras(@Valid @ModelAttribute("agendamentoLavanderia") AgendamentoServicoMedicoCruzeiro agendamentoCamareira,
                                      BindingResult bindingResult,
                                      Model model

    ) {
        if (bindingResult.hasErrors()) {
            return "cruzeiro/medico";
        }

        try {
            agendamentoService.criarAgendamento(agendamentoCamareira);
        } catch(HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            System.out.println(e.getMessage());
            return "cruzeiro/medico";
        }

        return "redirect:/cruzeiro/home";
    }
}