package com.grupo11.hootel.exceptions;

public class CamareirasIndisponiveisException extends HootelException {
    public CamareirasIndisponiveisException() {
        super("Não há horários de camareiras disponíveis");
    }
}
