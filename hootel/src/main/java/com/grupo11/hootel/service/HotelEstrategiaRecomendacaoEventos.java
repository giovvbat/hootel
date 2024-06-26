package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.Reserva;

import java.util.ArrayList;
import java.util.List;

public class HotelEstrategiaRecomendacaoEventos implements EstrategiaRecomendacaoEventos {
    @Override
    public List<Evento> recomendarEventos(List<Evento> eventos, Reserva reserva) {
        List<Evento> recomendados = new ArrayList<>();

        for(Evento evento : eventos){
            for(String preferencia : reserva.getPreferenciasEventos()) {
                if(evento.getCategorias().contains(preferencia) && !recomendados.contains(evento)) {
                    recomendados.add(evento);
                }
            }
        }

        return recomendados;
    }
}
