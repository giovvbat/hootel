package com.grupo11.hootel.exceptions;

public class ReservaInvalidaException extends HootelException {
    public ReservaInvalidaException() {
        super("Preencha todos os campos corretamente.");
    }
}
