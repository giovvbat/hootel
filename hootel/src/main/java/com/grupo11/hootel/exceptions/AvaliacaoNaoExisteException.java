package com.grupo11.hootel.exceptions;

public class AvaliacaoNaoExisteException extends HootelException {
    public AvaliacaoNaoExisteException() {
        super("Avaliação inexistente");
    }
}
