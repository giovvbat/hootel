package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.HorarioCamareira;

import java.util.List;

public interface HorarioCamareiraService {

    List<HorarioCamareira> getHorariosCamareiras();

    List<HorarioCamareira> getHorariosPin(Long pin);

    List<HorarioCamareira> getHorariosDisponiveis();

    HorarioCamareira criarHorario(Integer id, Long pin, List<String> servicos);

    //void updateHorario(Long pin);

    void deletarHorario(Long pin);
}
