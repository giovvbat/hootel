package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.ReservaRepository;
import com.grupo11.hootel.entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva lerReservaPin(Long pin) {

        Optional<Reserva> reservaOptional = reservaRepository.findById(pin);
        if (reservaOptional.isEmpty()) {
            throw new RuntimeException("PIN inválido");
        }

        return reservaOptional.get();
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
