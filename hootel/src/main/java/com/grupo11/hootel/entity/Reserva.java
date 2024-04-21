package com.grupo11.hootel.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="reserva")
public class Reserva {

    @Id
    @Column(name="pin")
    private long PIN;

    @ManyToMany
    @JoinTable(
            name = "participantes_eventos",
            joinColumns = @JoinColumn(name = "reserva_pin"),
            inverseJoinColumns = @JoinColumn(name = "evento_id")
    )
    private List<Evento> eventos;

    public Reserva() {
    }

    public Reserva(long PIN) {
        this.PIN = PIN;
    }

    public long getPIN() {
        return PIN;
    }

    public void setPIN(long PIN) {
        this.PIN = PIN;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void addEvento(Evento evento) {
        if (eventos == null) {
            eventos = new ArrayList<>();
        }

        eventos.add(evento);
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "PIN=" + PIN +
                '}';
    }
}
