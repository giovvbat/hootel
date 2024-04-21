package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.AvaliacaoDAO;
import com.grupo11.hootel.entity.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private AvaliacaoDAO avaliacaoDAO;

    @Autowired
    public AvaliacaoServiceImpl(AvaliacaoDAO avaliacaoDAO) {
        this.avaliacaoDAO = avaliacaoDAO;
    }

    @Override
    @Transactional
    public void criarAvaliacao(Avaliacao avaliacao) {
        avaliacaoDAO.criarAvaliacao(avaliacao);
    }
}
