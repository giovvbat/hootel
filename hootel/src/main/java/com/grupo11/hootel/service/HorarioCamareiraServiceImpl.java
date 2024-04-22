package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.HorarioCamareiraRepository;
import com.grupo11.hootel.entity.HorarioCamareira;
import com.grupo11.hootel.entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioCamareiraServiceImpl implements HorarioCamareiraService {

    private final HorarioCamareiraRepository horarioCamareiraRepository;
    private final ReservaService reservaService;

    @Autowired
    public HorarioCamareiraServiceImpl(HorarioCamareiraRepository horarioCamareiraRepository,
                                       ReservaService reservaService) {
        this.horarioCamareiraRepository = horarioCamareiraRepository;
        this.reservaService = reservaService;
    }

    @Override
    public List<HorarioCamareira> getHorariosCamareiras() {
        return horarioCamareiraRepository.findAll();
    }

    @Override
    public List<HorarioCamareira> getHorariosPin(long pin) {
        Reserva reserva = reservaService.lerReservaPin(pin);

        return horarioCamareiraRepository.findAllByReserva(reserva);
    }

    @Override
    public List<HorarioCamareira> getHorariosDisponiveis() {
        return horarioCamareiraRepository.findAllByReserva(null);
    }

    @Override
    public HorarioCamareira atualizarHorario(int id, long pin, List<String> servicos) {
        HorarioCamareira horarioCamareira = horarioCamareiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horário inválido"));

        if (horarioCamareira.getReserva() != null) {
            throw new RuntimeException("Horário já está reservado");
        }

        horarioCamareira.setReserva(reservaService.lerReservaPin(pin));
        horarioCamareira.setServicos(servicos);
        return horarioCamareiraRepository.save(horarioCamareira);
    }
}
