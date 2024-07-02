package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Alimentacao;

import java.util.List;

public interface AlimentacaoService {

    void addAlimentacao(Alimentacao alimentacao);

    void atualizarAlimentacao(Alimentacao alimentacao);

    List<Alimentacao> listarAlimentacoes(Class<? extends Alimentacao> type);
}
