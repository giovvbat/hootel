package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.InformacoesRepository;
import com.grupo11.hootel.entity.Informacoes;
import java.util.Optional;

import com.grupo11.hootel.exceptions.InformacoesIncompletasException;
import com.grupo11.hootel.exceptions.InformacoesNaoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InformacoesServiceImpl implements InformacoesService {
    private final InformacoesRepository informacoesRepository;

    @Autowired
    public InformacoesServiceImpl(InformacoesRepository informacoesRepository) {
        this.informacoesRepository = informacoesRepository;
    }

    @Override
    public Informacoes lerInformacoes() {
        Optional<Informacoes> informacoes = informacoesRepository.findById(1);
        if (informacoes.isEmpty()) {
            throw new InformacoesNaoExisteException();
        }

        return informacoes.get();
    }

    @Override
    @Transactional
    public void atualizarInformacoes(Informacoes informacoes) {
        informacoes.setId(1);

        if(informacoes.getNomeWifi() == null || informacoes.getSenhaWifi() == null ||
        informacoes.getNumeroRecepcao() == null || informacoes.getInicioCafe() == null ||
        informacoes.getFinalCafe() == null || informacoes.getInicioPiscina() == null ||
        informacoes.getFinalPiscina() == null) {
            throw new InformacoesIncompletasException();
        }

        informacoesRepository.save(informacoes);
    }
}

