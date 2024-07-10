package com.grupo11.hootel.exceptions;

public class RefeicaoIncompletaException extends HootelException {
    public RefeicaoIncompletaException() {
        super("Preencha todos os campos");
    }
}
