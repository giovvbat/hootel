package com.grupo11.hootel.exceptions;

public class CamareiraNaoSolicitadaException extends HoospedagemException {
    public CamareiraNaoSolicitadaException() {
        super("Você ainda não solicitou uma camareira");
    }
}
