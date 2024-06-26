package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Alimentacao;
import com.grupo11.hootel.entity.Reserva;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacaoAlimentacaoServiceImpl implements RecomendacaoAlimentacaoService {
    @Override
    public List<Alimentacao> recomendarAlimentacao(EstrategiaRecomendacaoAlimentacao estrategia, List<Alimentacao> alimentacoes, Reserva reserva) {
        return estrategia.recomendarAlimentacao(alimentacoes, reserva);
    }
}
