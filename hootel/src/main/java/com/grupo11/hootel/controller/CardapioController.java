package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Alimentacao;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CardapioController {
    private AlimentacaoService alimentacaoService;
    private ReservaService reservaService;
    private RecomendacaoAlimentacaoService recomendacaoService;

    public CardapioController(AlimentacaoService alimentacaoService,
                              ReservaService reservaService,
                              RecomendacaoAlimentacaoService recomendacaoService) {
        this.alimentacaoService = alimentacaoService;
        this.reservaService = reservaService;
        this.recomendacaoService = recomendacaoService;
    }

    @GetMapping("/cardapio")
    public String getCardapio(Model model) {
        try {
            List<Alimentacao> alimentacao = alimentacaoService.listarAlimentacoes();
            model.addAttribute("cardapio", alimentacao);
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("cardapio", new Alimentacao());
        }
        return "cardapio";
    }

    @PostMapping("/cardapio/recomendacao")
    public String recomendacao(@Valid @ModelAttribute("reserva") Reserva aReserva,
                               BindingResult bindingResult,
                               Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("alimentacoes", alimentacaoService.listarAlimentacoes());
            return "cardapio";
        }

        try {
            Reserva reserva = reservaService.lerReservaPin(aReserva.getPIN());
            EstrategiaRecomendacaoAlimentacao estrategia = new HotelEstrategiaRecomendacaoAlimentacao();
            List<Alimentacao> alimentacoes = alimentacaoService.listarAlimentacoes();

            model.addAttribute(
                    "recomendacao",
                    recomendacaoService.recomendarAlimentacao(estrategia, alimentacoes, reserva)
            );
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("alimentacoes", alimentacaoService.listarAlimentacoes());
        }

        return "cardapio";
    }
}
