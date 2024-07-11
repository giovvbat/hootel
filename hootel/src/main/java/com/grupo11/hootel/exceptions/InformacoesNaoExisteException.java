package com.grupo11.hootel.exceptions;

public class InformacoesNaoExisteException extends HoospedagemException {
    public InformacoesNaoExisteException() {
        super("Sem informações cadastradas");
    }
}
