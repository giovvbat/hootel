package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.EventoDAO;
import com.grupo11.hootel.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    private EventoDAO eventoDAO;

    @Autowired
    public EventoServiceImpl(EventoDAO eventoDAO) {
        this.eventoDAO = eventoDAO;
    }

    @Override
    @Transactional
    public void atualizarEvento(Evento evento) {
        eventoDAO.atualizarEvento(evento);
    }

    @Override
    @Transactional
    public void criarEvento(Evento evento) {
        eventoDAO.criarEvento(evento);
    }

    @Override
    public Evento lerEventoId(Integer id) {
        return eventoDAO.lerEventoId(id);
    }

    @Override
    public List<Evento> lerTodosEventos() {
        return eventoDAO.lerTodosEventos();
    }
}
