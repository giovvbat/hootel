package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Cardapio;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.CardapioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GerenteCardapioController {

    private final CardapioService cardapioService;

    @Autowired
    public GerenteCardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping("/mostrarCardapio")
    public String mostrarCardapio(Model model) {
        try {
            Cardapio cardapio = cardapioService.lerCardapio();
            model.addAttribute("cardapio", cardapio);
            System.out.println(cardapio);
        } catch (HootelException e) {
            model.addAttribute("cardapio", new Cardapio());
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "cardapio_gerente";
    }

    @PostMapping("/atualizaCardapio")
    public String atualizarCardapio(@Valid @ModelAttribute("cardapio") Cardapio cardapio,
                                    BindingResult bindingResult,
                                    Model model) {

        if (bindingResult.hasErrors()) {
            return "cardapio_gerente";
        }

        try {
            cardapioService.atualizarCardapio(cardapio);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "cardapio_gerente";
    }
}
