package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.RefeicaoHotel;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarHotel;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.RefeicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
public class GerenteRefeicaoHotelController {

    private final RefeicaoService refeicaoService;

    @Autowired
    public GerenteRefeicaoHotelController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @GetMapping("/mostrarCardapio")
    public String mostrarCardapio(Model model) {
        model.addAttribute("refeicao", new RefeicaoHotel());
        model.addAttribute("categorias", PreferenciaAlimentarHotel.values());

        return "hotel/cardapio_gerente";
    }

    @PostMapping("/adicionarCardapio")
    public String addrefeicaoEmCardapio(@Valid @ModelAttribute("cardapio") RefeicaoHotel refeicao,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("refeicao", new RefeicaoHotel());
            model.addAttribute("categorias", PreferenciaAlimentarHotel.values());
            return "hotel/cardapio_gerente";
        }

        try {
            refeicaoService.addRefeicao(refeicao);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("refeicao", new RefeicaoHotel());
        model.addAttribute("categorias", PreferenciaAlimentarHotel.values());
        return "hotel/cardapio_gerente";
    }

    @PostMapping("/atualizaCardapio")
    public String atualizarrefeicaoEmCardapio(@Valid @ModelAttribute("cardapio") RefeicaoHotel refeicao,
                                    BindingResult bindingResult,
                                    Model model) {

        if (bindingResult.hasErrors()) {
            return "hotel/cardapio_gerente";
        }

        try {
            refeicaoService.atualizarRefeicao(refeicao);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "hotel/cardapio_gerente";
    }
}
