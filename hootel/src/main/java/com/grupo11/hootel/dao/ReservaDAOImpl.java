package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Cardapio;
import com.grupo11.hootel.entity.Reserva;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservaDAOImpl implements ReservaDAO{

    private EntityManager entityManager;

    @Autowired
    public ReservaDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Reserva lerReservaPin(Integer pin) {
        return entityManager.find(Reserva.class, pin);
    }

    @Override
    public List<Reserva> lerTodasReservas() {
        TypedQuery<Reserva> query = entityManager.createQuery("FROM Reserva ", Reserva.class);
        return query.getResultList();
    }

    @Override
    public void deletarReserva(Reserva reserva) {
        entityManager.remove(reserva);
    }
}
