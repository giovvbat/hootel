package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.ObjetivosSpaResort;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarSpaResort;
import com.grupo11.hootel.entity.enums.PreferenciaEventoSpaResort;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventoSpaResort extends Evento {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaEventoSpaResort.class)
    @CollectionTable(name = "preferencias_eventos", joinColumns = @JoinColumn(name = "id_evento"))
    @Column(name = "preferencia_evento", nullable = false)
    @NotNull(message = "Selecione suas preferências de eventos")
    private List<PreferenciaEventoSpaResort> categorias;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = ObjetivosSpaResort.class)
    @CollectionTable(name = "objetivos", joinColumns = @JoinColumn(name = "id_evento"))
    @Column(name = "objetivo", nullable = false)
    @NotNull(message = "Selecione seus objetivos")
    private List<ObjetivosSpaResort> objetivos;

    @Override
    protected boolean validarEspecifico() {
        return !categorias.isEmpty() && !objetivos.isEmpty();
    }

    public EventoSpaResort() {
        this.categorias = new ArrayList<>();
        this.objetivos = new ArrayList<>();
    }

    public EventoSpaResort(Integer id, String horario, String lugar, String nome, LocalDate dataInicio, String descricao, List<Reserva> reservas, List<PreferenciaEventoSpaResort> categorias, List<ObjetivosSpaResort> objetivos) {
        super(id, horario, lugar, nome, dataInicio, descricao, reservas);
        this.categorias = categorias;
        this.objetivos = objetivos;
    }

    public List<PreferenciaEventoSpaResort> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<PreferenciaEventoSpaResort> categorias) {
        this.categorias = categorias;
    }

    public List<ObjetivosSpaResort> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivosSpaResort> objetivos) {
        this.objetivos = objetivos;
    }

    @Override
    public String toString() {
        return "EventoSpaResort{" +
                "categorias=" + categorias +
                ", objetivos=" + objetivos +
                '}';
    }
}
