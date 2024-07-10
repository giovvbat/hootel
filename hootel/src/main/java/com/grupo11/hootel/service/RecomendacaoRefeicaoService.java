package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Refeicao;
import com.grupo11.hootel.entity.Reserva;

import java.util.List;

public interface RecomendacaoRefeicaoService {
    List<Refeicao> recomendarRefeicao(EstrategiaRecomendacaoRefeicao estrategia, List<Refeicao> refeicoes, Reserva reserva);
}
