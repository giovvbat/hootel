package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.AlimentacaoRepository;
import com.grupo11.hootel.entity.Alimentacao;
import com.grupo11.hootel.exceptions.CardapioIncompletoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        if (!alimentacao.validar()) {
            throw new CardapioIncompletoException();
        }

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
    public List<Alimentacao> listarAlimentacoes(Class<? extends Alimentacao> type) {
        List<Alimentacao> alimentacoes = alimentacaoRepository.findAll()
                .stream().filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());

        return alimentacoes;
    }
}
