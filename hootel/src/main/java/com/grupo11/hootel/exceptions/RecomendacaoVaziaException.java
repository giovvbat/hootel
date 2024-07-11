package com.grupo11.hootel.exceptions;

public class RecomendacaoVaziaException extends HoospedagemException {
    public RecomendacaoVaziaException() {
        super("Sem recomendações para você no momento");
    }
}
