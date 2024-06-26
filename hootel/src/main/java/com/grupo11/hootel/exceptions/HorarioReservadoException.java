package com.grupo11.hootel.exceptions;

public class HorarioReservadoException extends HootelException {
    public HorarioReservadoException() {
        super("Horário já reservado");
    }
}
