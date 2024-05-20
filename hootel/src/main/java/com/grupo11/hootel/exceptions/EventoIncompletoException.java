package com.grupo11.hootel.exceptions;

public class EventoIncompletoException extends HootelException {
    public EventoIncompletoException() {
        super("Preencha todos os campos");
    }
}
