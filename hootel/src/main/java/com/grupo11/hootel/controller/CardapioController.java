package com.grupo11.hootel.controller;

import com.grupo11.hootel.service.CardapioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardapioController {
    private CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping("/cardapio")
    public String getCardapio() {
        return "cardapio";
    }
}
