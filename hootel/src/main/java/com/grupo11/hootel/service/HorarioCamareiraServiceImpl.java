package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.HorarioCamareiraRepository;
import com.grupo11.hootel.entity.HorarioCamareira;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.*;
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
    public List<HorarioCamareira> lerHorariosCamareiras() {
        List<HorarioCamareira> horarioCamareiras = horarioCamareiraRepository.findAll();

        if(horarioCamareiras.isEmpty()) {
            throw new CamareirasIndisponiveisException();
        }

        return horarioCamareiras;
    }

    @Override
    public List<HorarioCamareira> lerHorariosPin(Long pin) {
        Reserva reserva = reservaService.lerReservaPin(pin);

        List<HorarioCamareira> horarioCamareiras = horarioCamareiraRepository.findAllByReserva(reserva);

        if(horarioCamareiras.isEmpty()) {
            throw new CamareiraNaoSolicitadaException();
        }

        return horarioCamareiras;
    }

    @Override
    public List<HorarioCamareira> lerHorariosDisponiveis() {

        List<HorarioCamareira> horarioCamareiras = horarioCamareiraRepository.findAllByReserva(null);

        if(horarioCamareiras.isEmpty()) {
            throw new CamareirasIndisponiveisException();
        }

        return horarioCamareiras;
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

    @Override
    @Transactional
    public void deletarHorario(Long pin) {
        Reserva reserva = reservaService.lerReservaPin(pin);

        if(horarioCamareiraRepository.findAllByReserva(reserva).isEmpty()) {
            throw new CamareiraNaoSolicitadaException();
        }

        HorarioCamareira horarioCamareira = horarioCamareiraRepository.findAllByReserva(reserva).get(0);
        horarioCamareira.setReserva(null);
        horarioCamareiraRepository.save(horarioCamareira);
    }
}
