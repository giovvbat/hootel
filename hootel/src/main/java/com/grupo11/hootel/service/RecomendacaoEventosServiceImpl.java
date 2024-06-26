package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.Reserva;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacaoEventosServiceImpl implements RecomendacaoEventosService {
    @Override
    public List<Evento> recomendarEventos(EstrategiaRecomendacaoEventos estrategia, List<Evento> eventos, Reserva reserva) {
        return estrategia.recomendarEventos(eventos, reserva);
    }
}
