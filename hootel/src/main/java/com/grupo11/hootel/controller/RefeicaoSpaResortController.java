package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Refeicao;
import com.grupo11.hootel.entity.RefeicaoSpaResort;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaSpaResort;
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
@RequestMapping("/spa")
public class RefeicaoSpaResortController {
    private RefeicaoService refeicaoService;
    private ReservaService reservaService;
    private RecomendacaoRefeicaoService recomendacaoService;

    public RefeicaoSpaResortController(RefeicaoService refeicaoService,
                                   ReservaService reservaService,
                                   RecomendacaoRefeicaoService recomendacaoService) {
        this.refeicaoService = refeicaoService;
        this.reservaService = reservaService;
        this.recomendacaoService = recomendacaoService;
    }

    @ModelAttribute("refeicoes")
    public List<RefeicaoSpaResort> listarRefeicoes() {
        try {
            List<Refeicao> list = refeicaoService.listarRefeicoes(RefeicaoSpaResort.class);
            List<RefeicaoSpaResort> refeicoesSpaResort = new ArrayList<>();

            for (Refeicao refeicao : list) {
                refeicoesSpaResort.add((RefeicaoSpaResort) refeicao);
            }

            return refeicoesSpaResort;
        } catch (HootelException e) {
            return new ArrayList<>();
        }
    }

    @GetMapping("/refeicao")
    public String getrefeicao(Model model) {
        try {
            model.addAttribute("reserva", new ReservaSpaResort());
            model.addAttribute("recomendacao", new ArrayList<>());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("reserva", new ReservaSpaResort());
            model.addAttribute("recomendacao", new ArrayList<>());
        }
        return "spa/refeicao";
    }

    @GetMapping("/refeicao/recomendacao")
    public String recomendacao(@Valid @ModelAttribute("reserva") ReservaSpaResort aReserva,
                               BindingResult bindingResult,
                               Model model) {

        if(bindingResult.hasErrors()) {
            return "spa/refeicao";
        }

        try {
            Reserva reserva = reservaService.lerReservaPin(aReserva.getPIN());
            EstrategiaRecomendacaoRefeicao estrategia = new SpaResortEstrategiaRecomendacaoRefeicao();
            List<Refeicao> refeicoes = refeicaoService.listarRefeicoes(RefeicaoSpaResort.class);

            List<Refeicao> list = recomendacaoService.recomendarRefeicao(estrategia, refeicoes, reserva);
            List<RefeicaoSpaResort> recomendacoes = new ArrayList<>();

            for (Refeicao refeicao : list) {
                recomendacoes.add((RefeicaoSpaResort) refeicao);
            }

            model.addAttribute("recomendacao", recomendacoes);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "spa/refeicao";
    }
}