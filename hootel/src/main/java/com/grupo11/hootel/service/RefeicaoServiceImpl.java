package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.RefeicaoRepository;
import com.grupo11.hootel.entity.Refeicao;
import com.grupo11.hootel.exceptions.RefeicaoIncompletaException;
import com.grupo11.hootel.exceptions.RefeicaoNaoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefeicaoServiceImpl implements RefeicaoService {

    private final RefeicaoRepository refeicaoRepository;

    @Autowired
    public RefeicaoServiceImpl(RefeicaoRepository refeicaoRepository) {
        this.refeicaoRepository = refeicaoRepository;
    }


    @Override
    @Transactional
    public void addRefeicao(Refeicao refeicao) {
        if (!refeicao.validar()) {
            throw new RefeicaoIncompletaException();
        }

        refeicaoRepository.save(refeicao);
    }

    @Override
    @Transactional
    public void atualizarRefeicao(Refeicao refeicao) {
        if (refeicaoRepository.existsById(refeicao.getId())) {
            refeicaoRepository.save(refeicao);
        } else {
            throw new RefeicaoNaoExisteException();
        }
    }

    @Override
    public List<Refeicao> listarRefeicoes(Class<? extends Refeicao> type) {
        List<Refeicao> refeicoes = refeicaoRepository.findAll()
                .stream().filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());

        return refeicoes;
    }
}
