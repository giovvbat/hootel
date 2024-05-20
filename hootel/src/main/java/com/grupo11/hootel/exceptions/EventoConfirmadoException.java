package com.grupo11.hootel.exceptions;

public class EventoConfirmadoException extends HootelException {
    public EventoConfirmadoException() {
        super("Você já confirmou presença nesse evento");
    }
}
