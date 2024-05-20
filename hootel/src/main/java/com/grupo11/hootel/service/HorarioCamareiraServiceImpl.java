package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.HorarioCamareiraRepository;
import com.grupo11.hootel.entity.HorarioCamareira;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.HorarioInvalidoException;
import com.grupo11.hootel.exceptions.HorarioReservadoException;
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
    public List<HorarioCamareira> getHorariosPin(Long pin) {
        Reserva reserva = reservaService.lerReservaPin(pin);

        return horarioCamareiraRepository.findAllByReserva(reserva);
    }

    @Override
    public List<HorarioCamareira> getHorariosDisponiveis() {
        return horarioCamareiraRepository.findAllByReserva(null);
    }

    @Override
    public HorarioCamareira atualizarHorario(Integer id, Long pin, List<String> servicos) {
        Reserva reserva = reservaService.lerReservaPin(pin);

        HorarioCamareira horarioCamareira = horarioCamareiraRepository.findById(id)
                .orElseThrow(HorarioInvalidoException::new);

        if (horarioCamareira.getReserva() != null) {
            throw new HorarioReservadoException();
        }

        horarioCamareira.setReserva(reserva);
        horarioCamareira.setServicos(servicos);
        return horarioCamareiraRepository.save(horarioCamareira);
    }
}
