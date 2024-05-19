package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Evento;

import java.util.List;

public interface EventoService {
    void atualizarEvento(Evento evento);

    void criarEvento(Evento evento);

    Evento lerEventoId(Integer id);

    List<Evento> lerTodosEventos();

    void adicionarParticipante(Long pinReserva, int idEvento);

    void removerParticipante(Long pinReserva, int idEvento);
}
