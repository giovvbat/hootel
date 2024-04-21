package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.CardapioRepository;
import com.grupo11.hootel.entity.Cardapio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CardapioServiceImpl implements CardapioService {

    private final CardapioRepository cardapioRepository;

    @Autowired
    public CardapioServiceImpl(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    @Transactional
    public Cardapio atualizarCardapio(Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }

    @Override
    public Cardapio lerCardapio() {
        Optional<Cardapio> cardapio = cardapioRepository.findById(1);

        if (cardapio.isEmpty()) {
            throw new RuntimeException("Não há cardápio cadastrado");
        }

        return cardapio.get();
    }
}
