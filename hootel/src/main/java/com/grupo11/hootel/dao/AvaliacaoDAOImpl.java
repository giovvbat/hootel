package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Avaliacao;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AvaliacaoDAOImpl implements AvaliacaoDAO {

    private EntityManager entityManager;

    @Autowired
    public AvaliacaoDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void criarAvaliacao(Avaliacao avaliacao) {
        entityManager.persist(avaliacao);
    }
}
