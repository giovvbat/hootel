package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.*;
import com.grupo11.hootel.entity.enums.ObjetivosSpaResort;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarHotel;

import java.util.ArrayList;
import java.util.List;

public class SpaResortEstrategiaRecomendacaoAlimentacao implements EstrategiaRecomendacaoAlimentacao {

    @Override
    public List<Alimentacao> recomendarAlimentacao(List<Alimentacao> alimentacoes, Reserva reserva) {
        if (reserva instanceof ReservaSpaResort) {
            ReservaSpaResort reservaSpa = (ReservaSpaResort) reserva;
            List<Alimentacao> recomendados = new ArrayList<>();

            for (Alimentacao alimentacao : alimentacoes) {
                if (alimentacao instanceof AlimentacaoSpaResort) {
                    AlimentacaoSpaResort alimentacaoSpa = (AlimentacaoSpaResort) alimentacao;

                    for (ObjetivosSpaResort objetivo : alimentacaoSpa.getObjetivos()) {
                        if(alimentacaoSpa.getObjetivos().contains(objetivo)) {
                            recomendados.add(alimentacao);
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
