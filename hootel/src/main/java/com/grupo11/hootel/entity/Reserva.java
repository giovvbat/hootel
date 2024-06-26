package com.grupo11.hootel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="reserva")
public class Reserva {

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

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "preferencias_alimentacao", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "preferencia_alimentacao", nullable = false)
    @NotNull(message = "Selecione suas preferências de alimentação")
    private List<String> preferenciasAlimentacao;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "preferencias_eventos", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "preferencia_evento", nullable = false)
    @NotNull(message = "Selecione suas preferências de eventos")
    private List<String> preferenciasEventos;

    public Reserva() {

    }

    public Reserva(Long PIN, List<String> preferenciasAlimentacao, List<String> preferenciasEventos) {
        this.PIN = PIN;
        this.preferenciasAlimentacao = preferenciasAlimentacao;
        this.preferenciasEventos = preferenciasEventos;
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

    public @NotNull List<String> getPreferenciasAlimentacao() {
        return preferenciasAlimentacao;
    }

    public void setPreferenciasAlimentacao(@NotNull List<String> preferenciasAlimentacao) {
        this.preferenciasAlimentacao = preferenciasAlimentacao;
    }

    public @NotNull List<String> getPreferenciasEventos() {
        return preferenciasEventos;
    }

    public void setPreferenciasEventos(@NotNull List<String> preferenciasEventos) {
        this.preferenciasEventos = preferenciasEventos;
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
                ", preferenciasAlimentacao=" + preferenciasAlimentacao +
                ", preferenciasEventos=" + preferenciasEventos +
                '}';
    }
}
