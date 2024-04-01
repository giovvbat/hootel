package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Cardapio;

public interface CardapioDAO {

    void atualizarCardapio(Cardapio cardapio);

    Cardapio lerCardapio();

    void criarCardapio(Cardapio cardapio);
}
