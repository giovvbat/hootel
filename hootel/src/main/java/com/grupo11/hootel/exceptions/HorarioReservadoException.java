package com.grupo11.hootel.exceptions;

public class HorarioReservadoException extends HoospedagemException {
    public HorarioReservadoException() {
        super("Horário já reservado");
    }
}
