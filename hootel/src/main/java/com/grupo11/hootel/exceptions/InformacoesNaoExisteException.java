package com.grupo11.hootel.exceptions;

public class InformacoesNaoExisteException extends HootelException {
    public InformacoesNaoExisteException() {
        super("Sem informações cadastradas!");
    }
}
