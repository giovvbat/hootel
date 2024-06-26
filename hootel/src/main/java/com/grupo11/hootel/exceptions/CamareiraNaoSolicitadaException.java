package com.grupo11.hootel.exceptions;

public class CamareiraNaoSolicitadaException extends HootelException {
    public CamareiraNaoSolicitadaException() {
        super("Você ainda não solicitou uma camareira");
    }
}
