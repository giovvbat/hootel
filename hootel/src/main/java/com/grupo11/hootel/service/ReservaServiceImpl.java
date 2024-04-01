package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.ReservaDAO;
import com.grupo11.hootel.entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private ReservaDAO reservaDAO;

    @Autowired
    public ReservaServiceImpl(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
    }

    @Override
    public Reserva lerReservaPin(Integer pin) {
        return reservaDAO.lerReservaPin(pin);
    }

    @Override
    public List<Reserva> lerTodasReservas() {
        return reservaDAO.lerTodasReservas();
    }

    @Override
    @Transactional
    public void deletarReserva(Reserva reserva) {
        reservaDAO.deletarReserva(reserva);
    }
}
