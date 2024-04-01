package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.Reserva;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventoDAOImpl implements EventoDAO{

    private EntityManager entityManager;

    @Autowired
    public EventoDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void atualizarEvento(Evento evento) {
        entityManager.merge(evento);
    }

    @Override
    public void criarEvento(Evento evento) {
        entityManager.persist(evento);
    }

    @Override
    public Evento lerEventoId(Integer id) {
        return entityManager.find(Evento.class, id);
    }

    @Override
    public List<Evento> lerTodosEventos() {
        TypedQuery<Evento> query = entityManager.createQuery("FROM Evento ", Evento.class);
        return query.getResultList();
    }
}
