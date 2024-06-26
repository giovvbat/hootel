package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.Reserva;

import java.util.List;

public interface RecomendacaoEventosService {
    List<Evento> recomendarEventos(EstrategiaRecomendacaoEventos estrategia, List<Evento> eventos, Reserva reserva);
}
