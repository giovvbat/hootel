package com.grupo11.hootel.exceptions;

public class InformacoesIncompletasException extends HootelException {
    public InformacoesIncompletasException() {
        super("Preencha todos os campos");
    }
}
