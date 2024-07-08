package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.Reserva;

import java.util.ArrayList;
import java.util.List;

public class SpaResortEstrategiaRecomendacaoEventos implements EstrategiaRecomendacaoEventos {

    @Override
    public List<Evento> recomendarEventos(List<Evento> eventos, Reserva reserva) {
        /*recomendar de acordo com os objetivos ou perguntar a preferência logo de cara?*/
        return new ArrayList<>();
    }
}
