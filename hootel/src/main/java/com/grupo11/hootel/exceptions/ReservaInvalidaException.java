package com.grupo11.hootel.exceptions;

public class ReservaInvalidaException extends HoospedagemException {
    public ReservaInvalidaException() {
        super("Preencha todos os campos corretamente.");
    }
}
