package com.grupo11.hootel.exceptions;

public class AgendamentosIndisponiveisException extends HoospedagemException {
    public AgendamentosIndisponiveisException() {
        super("Não há horários de agendamento disponíveis");
    }
}
