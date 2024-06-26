package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Alimentacao;
import com.grupo11.hootel.entity.Reserva;

import java.util.List;

public interface EstrategiaRecomendacaoAlimentacao {
    List<Alimentacao> recomendarAlimentacao(List<Alimentacao> alimentacoes, Reserva reserva);
}
