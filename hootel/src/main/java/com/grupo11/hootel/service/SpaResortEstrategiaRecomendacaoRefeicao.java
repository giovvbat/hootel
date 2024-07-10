package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.*;
import com.grupo11.hootel.entity.enums.ObjetivosSpaResort;

import java.util.ArrayList;
import java.util.List;

public class SpaResortEstrategiaRecomendacaoRefeicao implements EstrategiaRecomendacaoRefeicao {

    @Override
    public List<Refeicao> recomendarRefeicao(List<Refeicao> Refeicoes, Reserva reserva) {
        if (reserva instanceof ReservaSpaResort) {
            ReservaSpaResort reservaSpa = (ReservaSpaResort) reserva;
            List<Refeicao> recomendados = new ArrayList<>();

            for (Refeicao refeicao : Refeicoes) {
                if (refeicao instanceof RefeicaoSpaResort) {
                    RefeicaoSpaResort refeicaoSpa = (RefeicaoSpaResort) refeicao;

                    for (ObjetivosSpaResort objetivo : reservaSpa.getObjetivos()) {
                        if(refeicaoSpa.getObjetivos().contains(objetivo)) {
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
