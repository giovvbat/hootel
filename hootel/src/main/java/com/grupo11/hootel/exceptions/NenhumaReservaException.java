package com.grupo11.hootel.exceptions;

public class NenhumaReservaException extends HootelException {
    public NenhumaReservaException() {
        super("Nenhuma reserva cadastrada");
    }
}
