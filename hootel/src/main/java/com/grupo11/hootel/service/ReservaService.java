package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Reserva;

import java.util.List;

public interface ReservaService {
    Reserva lerReservaPin(long pin);

    List<Reserva> lerTodasReservas();

    void deletarReserva(Reserva reserva);
}
