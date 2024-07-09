package com.grupo11.hootel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reserva {

    @Id
    @Column(name="pin")
    @NotNull(message = "Insira um PIN")
    @Positive(message = "O PIN deve ser um número positivo")
    private Long PIN;

    public Reserva() {

    }

    public Reserva(Long PIN) {
        this.PIN = PIN;
    }

    public Long getPIN() {
        return PIN;
    }

    public void setPIN(Long PIN) {
        this.PIN = PIN;
    }

    public boolean validar() {
        return PIN != null && PIN > 0 && validarEspecifico();
    }

    protected abstract boolean validarEspecifico();

    @Override
    public String toString() {
        return "Reserva{" +
                "PIN=" + PIN +
                '}';
    }
}
