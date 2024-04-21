package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.ReservaRepository;
import com.grupo11.hootel.entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva lerReservaPin(long pin) {
        return reservaRepository.findById(pin)
                .orElseThrow(() -> new RuntimeException("PIN inválido"));
    }

    @Override
    public List<Reserva> lerTodasReservas() {
        return reservaRepository.findAll();
    }

    @Override
    @Transactional
    public void deletarReserva(Reserva reserva) {
        reservaRepository.delete(reserva);
    }
}
