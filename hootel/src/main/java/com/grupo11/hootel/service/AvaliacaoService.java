package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Avaliacao;

import java.util.List;

public interface AvaliacaoService {

    void criarAvaliacao(Avaliacao avaliacao);

    void deletarAvaliacao(Avaliacao avaliacao);

    Avaliacao lerAvaliacao(Integer id);

    List<Avaliacao> listarTodasAvaliacoes();
}
