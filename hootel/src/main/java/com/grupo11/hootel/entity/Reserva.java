package com.grupo11.hootel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;


@MappedSuperclass
public abstract  class Reserva {

    @Id
    @Column(name="pin")
    @NotNull(message = "Insira um PIN")
    @Positive(message = "O PIN deve ser um número positivo")
    private Long PIN;

    @ManyToMany
    @JoinTable(
            name = "participantes_eventos",
            joinColumns = @JoinColumn(name = "reserva_pin"),
            inverseJoinColumns = @JoinColumn(name = "evento_id")
    )
    private List<Evento> eventos;

    public Reserva() {

    }

    public Reserva(Long PIN) {
        this.PIN = PIN;
    }

    public @NotNull(message = "Insira um PIN") @Positive(message = "O PIN deve ser um número positivo") Long getPIN() {
        return PIN;
    }

    public void setPIN(@NotNull(message = "Insira um PIN") @Positive(message = "O PIN deve ser um número positivo") Long PIN) {
        this.PIN = PIN;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void addEvento(Evento evento) {
        if (eventos == null) {
            eventos = new ArrayList<>();
        }

        eventos.add(evento);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "PIN=" + PIN +
                ", eventos=" + eventos +
                '}';
    }
}
