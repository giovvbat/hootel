package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.*;
import com.grupo11.hootel.entity.enums.PreferenciaEventoCruzeiro;
import com.grupo11.hootel.entity.enums.PreferenciaEventoHotel;

import java.util.ArrayList;
import java.util.List;

public class CruzeiroEstrategiaRecomendacaoEventos implements EstrategiaRecomendacaoEventos {
    @Override
    public List<Evento> recomendarEventos(List<Evento> eventos, Reserva reserva) {
        if (reserva instanceof ReservaCruzeiro) {
            ReservaCruzeiro reservaCruzeiro = (ReservaCruzeiro) reserva;
            List<Evento> recomendados = new ArrayList<>();

            for (Evento evento : eventos) {
                if (evento instanceof EventoCruzeiro) {
                    EventoCruzeiro eventoCruzeiro = (EventoCruzeiro) evento;
                    for (PreferenciaEventoCruzeiro preferencia : reservaCruzeiro.getPreferenciasEventos()) {
                        if (eventoCruzeiro.getCategorias().contains(preferencia) &&
                                reservaCruzeiro.getTurnoPreferido() == eventoCruzeiro.getTurno() &&
                                reservaCruzeiro.getIdade() >= eventoCruzeiro.getIdadeMinimaRecomendada()) {
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
