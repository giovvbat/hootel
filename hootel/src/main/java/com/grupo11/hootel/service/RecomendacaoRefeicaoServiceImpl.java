package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Refeicao;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.RecomendacaoVaziaException;
import com.grupo11.hootel.exceptions.ReservaInvalidaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacaoRefeicaoServiceImpl implements RecomendacaoRefeicaoService {
    @Override
    public List<Refeicao> recomendarRefeicao(EstrategiaRecomendacaoRefeicao estrategia,
                                             List<Refeicao> refeicoes, Reserva reserva) {
        List<Refeicao> recomendacao = estrategia.recomendarRefeicao(refeicoes, reserva);

        if (recomendacao.isEmpty()) {
            throw new RecomendacaoVaziaException();
        }
        return recomendacao;
    }
}
