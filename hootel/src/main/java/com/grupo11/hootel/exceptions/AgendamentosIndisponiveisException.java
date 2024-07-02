package com.grupo11.hootel.exceptions;

public class AgendamentosIndisponiveisException extends HootelException {
    public AgendamentosIndisponiveisException() {
        super("Não há horários de agendamento disponíveis");
    }
}
