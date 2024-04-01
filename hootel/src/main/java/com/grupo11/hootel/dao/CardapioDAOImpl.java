package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Cardapio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CardapioDAOImpl implements CardapioDAO{

    private EntityManager entityManager;

    @Autowired
    public CardapioDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Cardapio atualizarCardapio(Cardapio cardapio) {
        return entityManager.merge(cardapio);
    }

    @Override
    public Cardapio lerCardapio() {
        List<Cardapio> list = entityManager.createQuery("FROM Cardapio", Cardapio.class).getResultList();

        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }
}
