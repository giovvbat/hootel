package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Alimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioRepository extends JpaRepository<Alimentacao, Integer> {
}
