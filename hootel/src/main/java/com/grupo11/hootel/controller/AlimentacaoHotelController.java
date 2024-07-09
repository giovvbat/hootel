package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Alimentacao;
import com.grupo11.hootel.entity.AlimentacaoHotel;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaHotel;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AlimentacaoHotelController {
    private AlimentacaoService alimentacaoService;
    private ReservaService reservaService;
    private RecomendacaoAlimentacaoService recomendacaoService;

    public AlimentacaoHotelController(AlimentacaoService alimentacaoService,
                                      ReservaService reservaService,
                                      RecomendacaoAlimentacaoService recomendacaoService) {
        this.alimentacaoService = alimentacaoService;
        this.reservaService = reservaService;
        this.recomendacaoService = recomendacaoService;
    }

    @ModelAttribute("alimentacoes")
    public List<AlimentacaoHotel> listarAlimentacoes() {
        try {
            List<Alimentacao> list = alimentacaoService.listarAlimentacoes(AlimentacaoHotel.class);
            List<AlimentacaoHotel> alimentacoesHotel = new ArrayList<>();

            for (Alimentacao alimentacao : list) {
                alimentacoesHotel.add((AlimentacaoHotel) alimentacao);
            }

            return alimentacoesHotel;
        } catch (HootelException e) {
            return new ArrayList<>();
        }
    }

    @GetMapping("/alimentacao")
    public String getAlimentacao(Model model) {
        try {
            model.addAttribute("reserva", new ReservaHotel());
            model.addAttribute("recomendacao", new ArrayList<>());
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("reserva", new ReservaHotel());
            model.addAttribute("recomendacao", new ArrayList<>());
        }
        return "alimentacao";
    }

    @GetMapping("/alimentacao/recomendacao")
    public String recomendacao(@Valid @ModelAttribute("reserva") ReservaHotel aReserva,
                               BindingResult bindingResult,
                               Model model) {

        if(bindingResult.hasErrors()) {
            return "alimentacao";
        }

        try {
            Reserva reserva = reservaService.lerReservaPin(aReserva.getPIN());
            EstrategiaRecomendacaoAlimentacao estrategia = new HotelEstrategiaRecomendacaoAlimentacao();
            List<Alimentacao> alimentacoes = alimentacaoService.listarAlimentacoes(AlimentacaoHotel.class);

            List<Alimentacao> list = recomendacaoService.recomendarAlimentacao(estrategia, alimentacoes, reserva);
            List<AlimentacaoHotel> recomendacoes = new ArrayList<>();

            for (Alimentacao alimentacao : list) {
                recomendacoes.add((AlimentacaoHotel) alimentacao);
            }

            model.addAttribute("recomendacao", recomendacoes);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "alimentacao";
    }
}
