package com.grupo11.hootel.exceptions;

public class PINExistenteException extends HootelException {
    public PINExistenteException() {
        super("PIN já é utilizado para outra resevra");
    }
}
