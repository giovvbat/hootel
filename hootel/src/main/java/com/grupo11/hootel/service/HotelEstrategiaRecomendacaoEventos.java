package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaHotel;

import java.util.ArrayList;
import java.util.List;

public class HotelEstrategiaRecomendacaoEventos implements EstrategiaRecomendacaoEventos {
    @Override
    public List<Evento> recomendarEventos(List<Evento> eventos, Reserva reserva) {
        if (reserva instanceof ReservaHotel) {
            ReservaHotel reservaHotel = (ReservaHotel) reserva;
            List<Evento> recomendados = new ArrayList<>();

            for (Evento evento : eventos) {
                for (String preferencia : reservaHotel.getPreferenciasEventos()) {
                    if (evento.getCategorias().contains(preferencia) && !recomendados.contains(evento) && reservaHotel.getIdade() >= evento.getIdadeMinimaRecomendada()) {
                        recomendados.add(evento);
                    }
                }
            }

            return recomendados;
        } else {
            // Tratar o caso em que a reserva não é do tipo ReservaHotel
            return new ArrayList<>(); // Ou outra ação adequada
        }
    }

}

