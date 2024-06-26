package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.CardapioRepository;
import com.grupo11.hootel.entity.Alimentacao;
import com.grupo11.hootel.exceptions.CardapioIncompletoException;
import com.grupo11.hootel.exceptions.CardapioNaoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentacaoServiceImpl implements AlimentacaoService {

    private final CardapioRepository cardapioRepository;

    @Autowired
    public AlimentacaoServiceImpl(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    @Transactional
    public void atualizarAlimentacao(Alimentacao alimentacao) {
        alimentacao.setId(1);

        if(alimentacao.getOpcaoVegetariana() == null || alimentacao.getBebidas() == null ||
        alimentacao.getOpcaoCarnivora() == null) {
            throw new CardapioIncompletoException();
        }

        cardapioRepository.save(alimentacao);
    }

    @Override
    public List<Alimentacao> listarAlimentacoes() {
        return cardapioRepository.findAll();
    }
}
