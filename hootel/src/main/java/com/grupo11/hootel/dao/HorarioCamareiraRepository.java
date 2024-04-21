package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.HorarioCamareira;
import com.grupo11.hootel.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioCamareiraRepository extends JpaRepository<HorarioCamareira, Integer> {

    List<HorarioCamareira> findAllByReserva(Reserva reserva);
}