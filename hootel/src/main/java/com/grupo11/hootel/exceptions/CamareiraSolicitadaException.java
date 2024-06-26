package com.grupo11.hootel.exceptions;

public class CamareiraSolicitadaException extends HootelException {
    public CamareiraSolicitadaException() {
        super("Você já solicitou uma camareira hoje");
    }
}
