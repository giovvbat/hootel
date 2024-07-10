package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Refeicao;
import com.grupo11.hootel.entity.Reserva;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacaoRefeicaoServiceImpl implements RecomendacaoRefeicaoService {
    @Override
    public List<Refeicao> recomendarRefeicao(EstrategiaRecomendacaoRefeicao estrategia,
                                             List<Refeicao> refeicoes, Reserva reserva) {
        return estrategia.recomendarRefeicao(refeicoes, reserva);
    }
}
