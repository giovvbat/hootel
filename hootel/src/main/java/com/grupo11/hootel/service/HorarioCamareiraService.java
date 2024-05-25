package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.HorarioCamareira;

import java.util.List;

public interface HorarioCamareiraService {

    List<HorarioCamareira> lerHorariosCamareiras();

    List<HorarioCamareira> lerHorariosPin(Long pin);

    List<HorarioCamareira> lerHorariosDisponiveis();

    void criarHorario(HorarioCamareira horarioCamareira);

    void deletarHorario(Long pin);
}
