package com.grupo11.hootel.exceptions;

public class EventoIncompletoException extends HoospedagemException {
    public EventoIncompletoException() {
        super("Preencha todos os campos");
    }
}
