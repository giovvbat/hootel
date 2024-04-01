package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Evento;

import java.util.List;

public interface EventoDAO {

    void atualizarEvento(Evento evento);

    void criarEvento(Evento evento);

    Evento lerEventoId(Integer id);

    List<Evento> lerTodosEventos();
}
