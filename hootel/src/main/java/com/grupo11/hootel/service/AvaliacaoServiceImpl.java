package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.AvaliacaoRepository;
import com.grupo11.hootel.entity.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    @Transactional
    public void criarAvaliacao(Avaliacao avaliacao) {
        avaliacaoRepository.save(avaliacao);
    }
}
