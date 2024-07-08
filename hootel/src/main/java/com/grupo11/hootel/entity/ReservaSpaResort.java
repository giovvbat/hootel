package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ReservaSpaResort")
public class ReservaSpaResort extends Reserva {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaEventoSpaResort.class)
    @CollectionTable(name = "preferencias_eventos", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "preferencia_evento", nullable = false)
    @NotNull(message = "Selecione suas preferências de eventos")
    private List<PreferenciaEventoSpaResort> preferenciasEventos;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaAlimentarSpaResort.class)
    @CollectionTable(name = "preferencias_alimentacao", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "preferencia_alimentacao", nullable = false)
    @NotNull(message = "Selecione suas preferências de alimentação")
    private List<PreferenciaAlimentarSpaResort> preferenciasAlimentacao;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = ObjetivosSpaResort.class)
    @CollectionTable(name = "objetivos", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "objetivo", nullable = false)
    @NotNull(message = "Selecione suas preferências de alimentação")
    private List<ObjetivosSpaResort> objetivos;

    public ReservaSpaResort() {
        super();
        preferenciasEventos = new ArrayList<>();
        preferenciasAlimentacao = new ArrayList<>();
    }

    public ReservaSpaResort(Integer idade, List<PreferenciaEventoSpaResort> preferenciasEventos, List<PreferenciaAlimentarSpaResort> preferenciasAlimentares) {
        super();
        this.preferenciasEventos = preferenciasEventos;
        this.preferenciasAlimentacao = preferenciasAlimentares;
    }

    public List<PreferenciaEventoSpaResort> getPreferenciasEventos() {
        return preferenciasEventos;
    }

    public void setPreferenciasEventos(List<PreferenciaEventoSpaResort> preferenciasEventos) {
        this.preferenciasEventos = preferenciasEventos;
    }

    public List<PreferenciaAlimentarSpaResort> getPreferenciasAlimentacao() {
        return preferenciasAlimentacao;
    }

    public void setPreferenciasAlimentacao(List<PreferenciaAlimentarSpaResort> preferenciasAlimentacao) {
        this.preferenciasAlimentacao = preferenciasAlimentacao;
    }

    public List<ObjetivosSpaResort> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivosSpaResort> objetivos) {
        this.objetivos = objetivos;
    }

    @Override
    public boolean validar() {
        if(objetivos.isEmpty() || preferenciasAlimentacao.isEmpty() || preferenciasEventos.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReservaSpaResort{" +
                "preferenciasEventos=" + preferenciasEventos +
                ", preferenciasAlimentacao=" + preferenciasAlimentacao +
                ", objetivos=" + objetivos +
                '}';
    }
}
