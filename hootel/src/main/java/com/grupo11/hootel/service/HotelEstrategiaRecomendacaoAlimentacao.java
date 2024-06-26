package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Alimentacao;
import com.grupo11.hootel.entity.Reserva;

import java.util.ArrayList;
import java.util.List;

public class HotelEstrategiaRecomendacaoAlimentacao implements EstrategiaRecomendacaoAlimentacao {

    @Override
    public List<Alimentacao> recomendarAlimentacao(List<Alimentacao> alimentacoes, Reserva reserva) {
        List<Alimentacao> recomendados = new ArrayList<>();

        for (Alimentacao alimentacao : alimentacoes) {
            for (String preferencia : reserva.getPreferenciasAlimentacao()) {
                if(alimentacao.getCategorias().contains(preferencia) && !recomendados.contains(alimentacao)) {
                    recomendados.add(alimentacao);
                }
            }
        }

        return recomendados;
    }
}
