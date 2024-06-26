package com.grupo11.hootel.exceptions;

public class CardapioNaoExisteException extends HootelException {
    public CardapioNaoExisteException() {
        super("Cardápio não cadastrado");
    }
}
