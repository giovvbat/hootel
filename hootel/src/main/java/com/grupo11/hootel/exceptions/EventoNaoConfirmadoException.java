package com.grupo11.hootel.exceptions;

public class EventoNaoConfirmadoException extends HootelException {
    public EventoNaoConfirmadoException() {
        super("Sua presença não está confirmada para esse evento");
    }
}
