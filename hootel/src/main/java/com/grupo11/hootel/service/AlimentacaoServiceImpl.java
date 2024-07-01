package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.AlimentacaoRepository;
import com.grupo11.hootel.entity.Alimentacao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlimentacaoServiceImpl implements AlimentacaoService {

    private final AlimentacaoRepository alimentacaoRepository;

    @Autowired
    public AlimentacaoServiceImpl(AlimentacaoRepository alimentacaoRepository) {
        this.alimentacaoRepository = alimentacaoRepository;
    }


    @Override
    @Transactional
    public void addAlimentacao(Alimentacao alimentacao) {
        alimentacaoRepository.save(alimentacao);
    }

    @Override
    @Transactional
    public void atualizarAlimentacao(Alimentacao alimentacao) {
        if (alimentacaoRepository.existsById(alimentacao.getId())) {
            alimentacaoRepository.save(alimentacao);
        } else {
            throw new EntityNotFoundException("Alimentacao with id " + alimentacao.getId() + " not found");
        }
    }

    @Override
    public List<Alimentacao> listarAlimentacoes() {
        return alimentacaoRepository.findAll();
    }
}
