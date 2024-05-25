package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.HorarioCamareira;

import java.util.List;

public interface HorarioCamareiraService {

    List<HorarioCamareira> lerHorariosCamareiras();

    List<HorarioCamareira> lerHorariosPin(Long pin);

    List<HorarioCamareira> lerHorariosDisponiveis();

    HorarioCamareira criarHorario(HorarioCamareira horarioCamareira);

    //void updateHorario(Long pin);

    void deletarHorario(Long pin);
}
