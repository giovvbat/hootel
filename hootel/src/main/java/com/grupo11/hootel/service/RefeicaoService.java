package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Refeicao;

import java.util.List;

public interface RefeicaoService {

    void addRefeicao(Refeicao refeicao);

    void atualizarRefeicao(Refeicao refeicao);

    List<Refeicao> listarRefeicoes(Class<? extends Refeicao> type);
}
