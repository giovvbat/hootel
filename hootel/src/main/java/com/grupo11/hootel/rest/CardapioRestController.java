package com.grupo11.hootel.rest;

import com.grupo11.hootel.entity.Cardapio;
import com.grupo11.hootel.service.CardapioService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CardapioRestController {

    private CardapioService cardapioService;

    @Autowired
    public CardapioRestController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping("/cardapio")
    public Cardapio lerCardapio() {
        return cardapioService.lerCardapio();
    }

    @PutMapping("/cardapio")
    public Cardapio atualizarCardapio(@RequestBody Cardapio cardapio) {
        cardapio.setId(1);

        return cardapioService.atualizarCardapio(cardapio);
    }
}
