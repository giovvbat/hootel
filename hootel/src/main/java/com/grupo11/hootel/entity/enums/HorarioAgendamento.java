package com.grupo11.hootel.entity.enums;

import java.time.LocalTime;

public enum HorarioAgendamento {
    SETE(LocalTime.of(7, 0)),
    SETE_MEIA(LocalTime.of(7, 30)),
    OITO(LocalTime.of(8, 0)),
    OITO_MEIA(LocalTime.of(8, 30)),
    NOVE(LocalTime.of(9, 0)),
    NOVE_MEIA(LocalTime.of(9, 30)),
    DEZ(LocalTime.of(10, 0)),
    DEZ_MEIA(LocalTime.of(10, 30)),
    ONZE(LocalTime.of(11, 0)),
    ONZE_MEIA(LocalTime.of(11, 30)),
    TREZE(LocalTime.of(13, 0)),
    TREZE_MEIA(LocalTime.of(13, 30)),
    QUATORZE(LocalTime.of(14, 0)),
    QUATORZE_MEIA(LocalTime.of(14, 30)),
    QUINZE(LocalTime.of(15, 0)),
    QUINZE_MEIA(LocalTime.of(15, 30)),
    DEZESSEIS(LocalTime.of(16, 0)),
    DEZESSEIS_MEIA(LocalTime.of(16, 30)),
    DEZESSETE(LocalTime.of(17, 0)),
    DEZESSETE_MEIA(LocalTime.of(17, 30)),
    DEZOITO(LocalTime.of(18, 0)),
    DEZOITO_MEIA(LocalTime.of(18, 30)),
    DEZENOVE(LocalTime.of(19, 0)),
    DEZENOVE_MEIA(LocalTime.of(19, 30)),
    VINTE(LocalTime.of(20, 0)),
    VINTE_MEIA(LocalTime.of(20, 30)),
    VINTE_UMA(LocalTime.of(21, 0)),
    VINTE_UMA_MEIA(LocalTime.of(21, 30));

    private final LocalTime time;

    HorarioAgendamento(LocalTime time) {
        this.time = time;
    }

    private LocalTime getTime() {
        return time;
    }
}
