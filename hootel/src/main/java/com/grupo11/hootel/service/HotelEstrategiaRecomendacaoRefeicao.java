package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.*;
import com.grupo11.hootel.entity.RefeicaoHotel;
import com.grupo11.hootel.entity.Refeicao;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarHotel;

import java.util.ArrayList;
import java.util.List;

public class HotelEstrategiaRecomendacaoRefeicao implements EstrategiaRecomendacaoRefeicao {

    @Override
    public List<Refeicao> recomendarRefeicao(List<Refeicao> Refeicoes, Reserva reserva) {
        if (reserva instanceof ReservaHotel) {
            ReservaHotel reservaHotel = (ReservaHotel) reserva;
            List<Refeicao> recomendados = new ArrayList<>();

            for (Refeicao refeicao : Refeicoes) {
                if (refeicao instanceof RefeicaoHotel) {
                    RefeicaoHotel RefeicaoHotel = (RefeicaoHotel) refeicao;

                    boolean podeSerRecomendado = true;
                    for (PreferenciaAlimentarHotel preferencia : reservaHotel.getPreferenciasAlimentares()) {
                        if (!RefeicaoHotel.getCategorias().contains(preferencia)) {
                            podeSerRecomendado = false;
                            break;
                        }
                    }

                    if(podeSerRecomendado) {
                        recomendados.add(refeicao);
                    }
                }

            }

            return recomendados;
        } else {
            return new ArrayList<>();
        }
    }

}