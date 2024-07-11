package com.grupo11.hootel.exceptions;

public class AvaliacaoNaoExisteException extends HoospedagemException {
    public AvaliacaoNaoExisteException() {
        super("Avaliação inexistente");
    }
}
