package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.AgendamentoServicoMedicoCruzeiro;
import com.grupo11.hootel.entity.ReservaCruzeiro;
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

    @ModelAttribute("horarios")
    public HorarioAgendamento[] getHorariosAgendamento() {
        return HorarioAgendamento.values();
    }

    @GetMapping("/medico")
    public String mostrarCamareiras(Model model) {
        return "cruzeiro/medico";
    }

    @PostMapping("/processarMedico")
    public String processarCamareiras(@Valid @ModelAttribute("agendamentoMedico") AgendamentoServicoMedicoCruzeiro agendamentoCamareira,
                                      BindingResult bindingResult,
                                      Model model

    ) {
        if (bindingResult.hasErrors()) {
            return "cruzeiro/medico";
        }

        try {
            agendamentoService.criarAgendamento(agendamentoCamareira);
        } catch(HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "cruzeiro/medico";
        }

        return "redirect:/cruzeiro/home";
    }
}