package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.*;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cruzeiro")
public class RefeicaoCruzeiroController {
    private RefeicaoService refeicaoService;
    private ReservaService reservaService;
    private RecomendacaoRefeicaoService recomendacaoService;
    private CruzeiroEstrategiaRecomendacaoRefeicao estrategia;

    public RefeicaoCruzeiroController(RefeicaoService refeicaoService,
                                   ReservaService reservaService,
                                   RecomendacaoRefeicaoService recomendacaoService) {
        this.refeicaoService = refeicaoService;
        this.reservaService = reservaService;
        this.recomendacaoService = recomendacaoService;
        this.estrategia = new CruzeiroEstrategiaRecomendacaoRefeicao();
    }

    @ModelAttribute("refeicoes")
    public List<RefeicaoCruzeiro> listarRefeicoes() {
        try {
            List<Refeicao> list = refeicaoService.listarRefeicoes(RefeicaoCruzeiro.class);
            List<RefeicaoCruzeiro> refeicoesCruzeiro = new ArrayList<>();

            for (Refeicao refeicao : list) {
                refeicoesCruzeiro.add((RefeicaoCruzeiro) refeicao);
            }

            return refeicoesCruzeiro;
        } catch (HootelException e) {
            return new ArrayList<>();
        }
    }

    @GetMapping("/refeicao")
    public String getrefeicao(Model model) {
        try {
            model.addAttribute("reserva", new ReservaCruzeiro());
            model.addAttribute("recomendacao", new ArrayList<>());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("reserva", new ReservaCruzeiro());
            model.addAttribute("recomendacao", new ArrayList<>());
        }
        return "cruzeiro/refeicao";
    }

    @GetMapping("/refeicao/recomendacao")
    public String recomendacao(@Valid @ModelAttribute("reserva") ReservaCruzeiro aReserva,
                               BindingResult bindingResult,
                               Model model) {

        if(bindingResult.hasErrors()) {
            return "cruzeiro/refeicao";
        }

        try {
            Reserva reserva = reservaService.lerReservaPin(aReserva.getPIN());
            List<Refeicao> refeicoes = refeicaoService.listarRefeicoes(RefeicaoCruzeiro.class);

            List<Refeicao> list = recomendacaoService.recomendarRefeicao(estrategia, refeicoes, reserva);
            List<RefeicaoCruzeiro> recomendacoes = new ArrayList<>();

            for (Refeicao refeicao : list) {
                recomendacoes.add((RefeicaoCruzeiro) refeicao);
            }

            model.addAttribute("recomendacao", recomendacoes);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "cruzeiro/refeicao";
    }
}
