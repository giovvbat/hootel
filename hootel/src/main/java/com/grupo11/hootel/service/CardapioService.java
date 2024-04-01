package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Cardapio;

public interface CardapioService {

    void atualizarCardapio(Cardapio cardapio);

    Cardapio lerCardapio();

    void criarCardapio(Cardapio cardapio);
}
