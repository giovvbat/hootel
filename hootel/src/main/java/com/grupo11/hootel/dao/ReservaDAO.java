package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.Reserva;

import java.util.List;

public interface ReservaDAO {

    Reserva lerReservaPin(long pin);

    List<Reserva> lerTodasReservas();

    void deletarReserva(Reserva reserva);

}
