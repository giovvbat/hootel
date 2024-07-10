package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.*;
import com.grupo11.hootel.entity.RefeicaoHotel;
import com.grupo11.hootel.entity.Refeicao;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RefeicaoHotelController {
    private RefeicaoService refeicaoService;
    private ReservaService reservaService;
    private RecomendacaoRefeicaoService recomendacaoService;

    public RefeicaoHotelController(RefeicaoService refeicaoService,
                                   ReservaService reservaService,
                                   RecomendacaoRefeicaoService recomendacaoService) {
        this.refeicaoService = refeicaoService;
        this.reservaService = reservaService;
        this.recomendacaoService = recomendacaoService;
    }

    @ModelAttribute("refeicoes")
    public List<RefeicaoHotel> listarRefeicoes() {
        try {
            List<Refeicao> list = refeicaoService.listarRefeicoes(RefeicaoHotel.class);
            List<RefeicaoHotel> refeicoesHotel = new ArrayList<>();

            for (Refeicao refeicao : list) {
                refeicoesHotel.add((RefeicaoHotel) refeicao);
            }

            return refeicoesHotel;
        } catch (HootelException e) {
            return new ArrayList<>();
        }
    }

    @GetMapping("/refeicao")
    public String getrefeicao(Model model) {
        try {
            model.addAttribute("reserva", new ReservaHotel());
            model.addAttribute("recomendacao", new ArrayList<>());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("reserva", new ReservaHotel());
            model.addAttribute("recomendacao", new ArrayList<>());
        }
        return "refeicao";
    }

    @GetMapping("/refeicao/recomendacao")
    public String recomendacao(@Valid @ModelAttribute("reserva") ReservaHotel aReserva,
                               BindingResult bindingResult,
                               Model model) {

        if(bindingResult.hasErrors()) {
            return "refeicao";
        }

        try {
            Reserva reserva = reservaService.lerReservaPin(aReserva.getPIN());
            EstrategiaRecomendacaoRefeicao estrategia = new HotelEstrategiaRecomendacaoRefeicao();
            List<Refeicao> refeicoes = refeicaoService.listarRefeicoes(RefeicaoHotel.class);

            List<Refeicao> list = recomendacaoService.recomendarRefeicao(estrategia, refeicoes, reserva);
            List<RefeicaoHotel> recomendacoes = new ArrayList<>();

            for (Refeicao refeicao : list) {
                recomendacoes.add((RefeicaoHotel) refeicao);
            }

            model.addAttribute("recomendacao", recomendacoes);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "refeicao";
    }
}
