package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.CardapioRepository;
import com.grupo11.hootel.dao.InformacoesRepository;
import com.grupo11.hootel.entity.Cardapio;
import com.grupo11.hootel.entity.Informacoes;
import java.util.Optional;
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
            throw new RuntimeException("Não há informações cadastradas");
        }

        return informacoes.get();
    }
}

