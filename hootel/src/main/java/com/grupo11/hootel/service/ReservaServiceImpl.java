package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.ReservaRepository;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.NenhumaReservaException;
import com.grupo11.hootel.exceptions.PINExistenteException;
import com.grupo11.hootel.exceptions.PinInvalidoException;
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
            throw new PinInvalidoException();
        }

        return reservaOptional.get();
    }

    @Override
    public List<Reserva> lerTodasReservas() {
        List<Reserva> reservas = reservaRepository.findAll();

        if(reservas.isEmpty()) {
            throw new NenhumaReservaException();
        }
        return reservaRepository.findAll();
    }

    @Override
    @Transactional
    public void deletarReserva(Reserva reserva) {

        Optional<Reserva> reservaOptional = reservaRepository.findById(reserva.getPIN());
        if (reservaOptional.isEmpty()) {
            throw new PinInvalidoException();
        }

        reservaRepository.delete(reserva);
    }

    @Override
    @Transactional
    public void criarReserva(Reserva reserva) {
        //gerar Pin aleatório

        Optional<Reserva> reservaOptionalExistente = reservaRepository.findById(reserva.getPIN());
        if (!reservaOptionalExistente.isEmpty()) {
            throw new PINExistenteException();
        }

        reservaRepository.save(reserva);
    }

    @Override
    @Transactional
    public void updateReserva(Reserva reserva, Long pin) {

        //gerar Pin aleatório

        Optional<Reserva> reservaOptional = reservaRepository.findById(reserva.getPIN());
        if(reservaOptional.isEmpty()) {
            throw new PinInvalidoException();
        }

        Optional<Reserva> reservaOptionalExistente = reservaRepository.findById(pin);
        if(!reservaOptionalExistente.isEmpty()) {
            throw new PINExistenteException();
        }

        reserva.setPIN(pin);
        reservaRepository.save(reserva);
    }
}
