package com.grupo11.hootel.exceptions;

public class RefeicaoNaoExisteException extends HoospedagemException {
    public RefeicaoNaoExisteException() {
        super("Refeição não cadastrada");
    }
}
