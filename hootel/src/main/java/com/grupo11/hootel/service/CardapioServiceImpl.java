package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.CardapioDAO;
import com.grupo11.hootel.entity.Cardapio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardapioServiceImpl implements CardapioService {

    private CardapioDAO cardapioDAO;

    @Autowired
    public CardapioServiceImpl(CardapioDAO cardapioDAO) {
        this.cardapioDAO = cardapioDAO;
    }

    @Override
    @Transactional
    public void atualizarCardapio(Cardapio cardapio) {
        cardapioDAO.atualizarCardapio(cardapio);
    }

    @Override
    public Cardapio lerCardapio() {
        return cardapioDAO.lerCardapio();
    }

    @Override
    @Transactional
    public void criarCardapio(Cardapio cardapio) {
        cardapioDAO.criarCardapio(cardapio);
    }
}
