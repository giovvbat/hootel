package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.HorarioCamareiraRepository;
import com.grupo11.hootel.entity.HorarioCamareira;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.CamareiraNaoSolicitadaException;
import com.grupo11.hootel.exceptions.CamareiraSolicitadaException;
import com.grupo11.hootel.exceptions.HorarioInvalidoException;
import com.grupo11.hootel.exceptions.HorarioReservadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public HorarioCamareira criarHorario(Integer id, Long pin, List<String> servicos) {
        Reserva reserva = reservaService.lerReservaPin(pin);

        if(!horarioCamareiraRepository.findAllByReserva(reserva).isEmpty()) {
            throw new CamareiraSolicitadaException();
        }

        HorarioCamareira horarioCamareira = horarioCamareiraRepository.findById(id)
                .orElseThrow(HorarioInvalidoException::new);

        if (horarioCamareira.getReserva() != null) {
            throw new HorarioReservadoException();
        }

        horarioCamareira.setReserva(reserva);
        horarioCamareira.setServicos(servicos);
        return horarioCamareiraRepository.save(horarioCamareira);
    }


//    @Override
//    @Transactional
//    public void updateHorario(Long pin) {
//        Reserva reserva = reservaService.lerReservaPin(pin);
//
//        if(horarioCamareiraRepository.findAllByReserva(reserva).isEmpty()) {
//            throw new CamareiraNaoSolicitadaException();
//        }
//
//        horarioCamareiraRepository.findAllByReserva(reserva).getFirst();
//
//    }

    @Override
    @Transactional
    public void deletarHorario(Long pin) {
        Reserva reserva = reservaService.lerReservaPin(pin);

        if(horarioCamareiraRepository.findAllByReserva(reserva).isEmpty()) {
            throw new CamareiraNaoSolicitadaException();
        }

        horarioCamareiraRepository.delete(horarioCamareiraRepository.findAllByReserva(reserva).getFirst());
    }
}
