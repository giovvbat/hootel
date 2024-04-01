package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Cardapio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CardapioDAOImpl implements CardapioDAO{

    private EntityManager entityManager;

    @Autowired
    public CardapioDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void atualizarCardapio(Cardapio cardapio) {
        entityManager.merge(cardapio);
    }

    @Override
    public Cardapio lerCardapio() {
        TypedQuery<Cardapio> query = entityManager.createQuery("FROM Cardapio", Cardapio.class);
        return query.getResultList().get(0);
    }

    @Override
    @Transactional
    public void criarCardapio(Cardapio cardapio) {
        entityManager.persist(cardapio);
    }
}
