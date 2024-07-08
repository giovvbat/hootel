package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.EventoSpaResort;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaSpaResort;
import com.grupo11.hootel.entity.enums.ObjetivosSpaResort;

import java.util.ArrayList;
import java.util.List;

public class SpaResortEstrategiaRecomendacaoEventos implements EstrategiaRecomendacaoEventos {

    @Override
    public List<Evento> recomendarEventos(List<Evento> eventos, Reserva reserva) {
        if (reserva instanceof ReservaSpaResort) {
            ReservaSpaResort reservaSpa = (ReservaSpaResort) reserva;
            List<Evento> recomendados = new ArrayList<>();

            for (Evento evento : eventos) {
                if (evento instanceof EventoSpaResort) {
                    EventoSpaResort eventoSpa = (EventoSpaResort) evento;
                    for (ObjetivosSpaResort objetivo : reservaSpa.getObjetivos()) {
                        if (eventoSpa.getObjetivos().contains(objetivo)) {
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
