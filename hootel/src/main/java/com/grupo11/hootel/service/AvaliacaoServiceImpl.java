package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.AvaliacaoRepository;
import com.grupo11.hootel.entity.Avaliacao;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.AvaliacaoNaoExisteException;
import com.grupo11.hootel.exceptions.NenhumaAvaliacaoException;
import com.grupo11.hootel.exceptions.NenhumaReservaException;
import com.grupo11.hootel.exceptions.PinInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional
    public void deletarAvaliacao(Avaliacao avaliacao) {

        Optional<Avaliacao> avaliacaoOptional = avaliacaoRepository.findById(avaliacao.getId_avaliacao());
        if (avaliacaoOptional.isEmpty()) {
            throw new AvaliacaoNaoExisteException();
        }

        avaliacaoRepository.delete(avaliacao);

    }

    @Override
    public Avaliacao lerAvaliacao(Integer id) {

        Optional<Avaliacao> avaliacaoOptional = avaliacaoRepository.findById(id);
        if (avaliacaoOptional.isEmpty()) {
            throw new AvaliacaoNaoExisteException();
        }

        return avaliacaoOptional.get();
    }

    @Override
    public List<Avaliacao> listarTodasAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

        if(avaliacoes.isEmpty()) {
            throw new NenhumaAvaliacaoException();
        }
        return avaliacaoRepository.findAll();
    }
}
