package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.HorarioCamareira;
import com.grupo11.hootel.entity.Reserva;

import java.util.List;

public interface HorarioCamareiraService {

    List<HorarioCamareira> getHorariosCamareiras();
    List<HorarioCamareira> getHorariosPin(int pin);
    List<HorarioCamareira> getHorariosDisponiveis();
    HorarioCamareira atualizarHorario(int id, long pin);
}
