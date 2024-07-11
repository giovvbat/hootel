package com.grupo11.hootel.exceptions;

public class NenhumaReservaException extends HoospedagemException {
    public NenhumaReservaException() {
        super("Nenhuma reserva cadastrada");
    }
}
