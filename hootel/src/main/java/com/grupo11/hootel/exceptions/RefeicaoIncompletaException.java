package com.grupo11.hootel.exceptions;

public class RefeicaoIncompletaException extends HoospedagemException {
    public RefeicaoIncompletaException() {
        super("Preencha todos os campos");
    }
}
