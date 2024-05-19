package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Cardapio;
import com.grupo11.hootel.entity.Informacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformacoesRepository extends JpaRepository<Informacoes, Integer> {
}
