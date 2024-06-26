package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
