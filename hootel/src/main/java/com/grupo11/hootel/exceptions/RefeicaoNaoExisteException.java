package com.grupo11.hootel.exceptions;

public class RefeicaoNaoExisteException extends HootelException {
    public RefeicaoNaoExisteException() {
        super("Refeição não cadastrado");
    }
}
