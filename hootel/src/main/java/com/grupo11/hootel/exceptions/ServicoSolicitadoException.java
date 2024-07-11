package com.grupo11.hootel.exceptions;

public class ServicoSolicitadoException extends HoospedagemException {
    public ServicoSolicitadoException() {
        super("Você já solicitou este tipo de serviço hoje");
    }
}
