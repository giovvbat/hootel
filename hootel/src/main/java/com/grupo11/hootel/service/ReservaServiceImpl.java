package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.ReservaRepository;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.ReservaHotel;
import com.grupo11.hootel.exceptions.NenhumaReservaException;
import com.grupo11.hootel.exceptions.PinInvalidoException;
import com.grupo11.hootel.exceptions.ReservaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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
    public List<Reserva> lerTodasReservas(Class<? extends Reserva> type) {
        List<Reserva> reservas = reservaRepository.findAll()
                .stream().filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());

        if(reservas.isEmpty()) {
            throw new NenhumaReservaException();
        }
        return reservas;
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
    public Reserva criarReserva(Reserva reserva) {

        Random random = new Random();
        Long numeroAleatorio = 1000 + random.nextLong(9000);

        while (reservaRepository.findById(numeroAleatorio).isPresent()) {
            numeroAleatorio = 1000 + random.nextLong(9000);
        }


        reserva.setPIN(numeroAleatorio);
        reservaRepository.save(reserva);

        return reserva;
    }

    /*
    @Override
    @Transactional
    public void updateReserva(Reserva reserva) {

        Random random = new Random();
        Long numeroAleatorio = 1000 + random.nextLong(9000);

        while (reservaRepository.findById(numeroAleatorio).isPresent()) {
            numeroAleatorio = 1000 + random.nextLong(9000);
        }

        Optional<Reserva> reservaOptional = reservaRepository.findById(reserva.getPIN());
        if(reservaOptional.isEmpty()) {
            throw new PinInvalidoException();
        }

        reserva.setPIN(numeroAleatorio);
        reservaRepository.save(reserva);
    }
     */
}
