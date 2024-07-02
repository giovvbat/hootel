package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.EventoHotel;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaHotel;
import com.grupo11.hootel.entity.enums.PreferenciaEventoHotel;

import java.util.ArrayList;
import java.util.List;

public class HotelEstrategiaRecomendacaoEventos implements EstrategiaRecomendacaoEventos {
    @Override
    public List<Evento> recomendarEventos(List<Evento> eventos, Reserva reserva) {
        if (reserva instanceof ReservaHotel) {
            ReservaHotel reservaHotel = (ReservaHotel) reserva;
            List<Evento> recomendados = new ArrayList<>();

            for (Evento evento : eventos) {
                if (evento instanceof EventoHotel) {
                    EventoHotel eventoHotel = (EventoHotel) evento;
                    for (PreferenciaEventoHotel preferencia : reservaHotel.getPreferenciasEventos()) {
                        if (eventoHotel.getCategorias().contains(preferencia) &&
                                reservaHotel.getIdade() >= eventoHotel.getIdadeMinimaRecomendada()) {
                            recomendados.add(evento);
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

