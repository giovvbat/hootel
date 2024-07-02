package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.Reserva;

import java.util.List;

public interface ReservaService {
    Reserva lerReservaPin(Long pin);

    List<Reserva> lerTodasReservas(Class<? extends Reserva> type);

    void deletarReserva(Reserva reserva);

    Reserva criarReserva(Reserva reserva);

    /*
    void updateReserva(Reserva reserva);
     */
}
