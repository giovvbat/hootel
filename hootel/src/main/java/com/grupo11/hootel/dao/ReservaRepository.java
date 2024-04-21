package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
