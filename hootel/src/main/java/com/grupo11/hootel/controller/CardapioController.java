package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Cardapio;
import com.grupo11.hootel.service.CardapioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardapioController {
    private CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping("/cardapio")
    public String getCardapio(Model model) {
        try {
            Cardapio cardapio = cardapioService.lerCardapio();
            model.addAttribute("cardapio", cardapio);
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "cardapio-error";
        }
        return "cardapio";
    }
}
