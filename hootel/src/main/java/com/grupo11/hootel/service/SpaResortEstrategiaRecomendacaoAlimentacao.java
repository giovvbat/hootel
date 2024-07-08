package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Alimentacao;
import com.grupo11.hootel.entity.Reserva;

import java.util.ArrayList;
import java.util.List;

public class SpaResortEstrategiaRecomendacaoAlimentacao implements EstrategiaRecomendacaoAlimentacao {

    @Override
    public List<Alimentacao> recomendarAlimentacao(List<Alimentacao> alimentacoes, Reserva reserva) {
        /*recomendar de acordo com os objetivos ou perguntar a preferência logo de cara?*/
        return new ArrayList<>();
    }
}
