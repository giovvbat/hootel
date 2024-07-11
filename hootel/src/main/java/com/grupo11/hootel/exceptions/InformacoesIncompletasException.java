package com.grupo11.hootel.exceptions;

public class InformacoesIncompletasException extends HoospedagemException {
    public InformacoesIncompletasException() {
        super("Preencha todos os campos");
    }
}
