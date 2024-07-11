package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.*;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarCruzeiro;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarHotel;

import java.util.ArrayList;
import java.util.List;

public class CruzeiroEstrategiaRecomendacaoRefeicao implements EstrategiaRecomendacaoRefeicao {
    @Override
    public List<Refeicao> recomendarRefeicao(List<Refeicao> Refeicoes, Reserva reserva) {
        if (reserva instanceof ReservaCruzeiro) {
            ReservaCruzeiro reservaCruzeiro = (ReservaCruzeiro) reserva;
            List<Refeicao> recomendados = new ArrayList<>();

            for (Refeicao refeicao : Refeicoes) {
                if (refeicao instanceof RefeicaoCruzeiro) {
                    RefeicaoCruzeiro RefeicaoCruzeiro = (RefeicaoCruzeiro) refeicao;

                    for (PreferenciaAlimentarCruzeiro preferencia : reservaCruzeiro.getPreferenciasAlimentares()) {
                        if (RefeicaoCruzeiro.getCategorias().contains(preferencia)) {
                            recomendados.add(refeicao);
                            break;
                        }
                    }
                }
            }

            return recomendados;
        } else {
            return new ArrayList<>();
        }
    }
}
