package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.CardapioRepository;
import com.grupo11.hootel.entity.Cardapio;
import com.grupo11.hootel.exceptions.CardapioIncompletoException;
import com.grupo11.hootel.exceptions.CardapioNaoExisteException;
import com.grupo11.hootel.exceptions.InformacoesIncompletasException;
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
    public void atualizarCardapio(Cardapio cardapio) {
        cardapio.setId(1);

        if(cardapio.getOpcaoVegetariana() == null || cardapio.getBebidas() == null ||
        cardapio.getOpcaoCarnivora() == null) {
            throw new CardapioIncompletoException();
        }

        cardapioRepository.save(cardapio);
    }

    @Override
    public Cardapio lerCardapio() {
        Optional<Cardapio> cardapio = cardapioRepository.findById(1);
        if (cardapio.isEmpty()) {
            throw new CardapioNaoExisteException();
        }

        return cardapio.get();
    }
}
