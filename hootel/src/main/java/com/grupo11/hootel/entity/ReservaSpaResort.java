package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ReservaSpaResort")
public class ReservaSpaResort extends Reserva {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = ObjetivosSpaResort.class)
    @CollectionTable(name = "objetivos_spa", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "objetivo", nullable = false)
    @NotNull(message = "Selecione seus objetivos")
    private List<ObjetivosSpaResort> objetivos;

    public ReservaSpaResort() {
        super();
        objetivos = new ArrayList<>();
    }

    public ReservaSpaResort(Long PIN, List<ObjetivosSpaResort> objetivos) {
        super(PIN);
        this.objetivos = objetivos;
    }

    public List<ObjetivosSpaResort> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivosSpaResort> objetivos) {
        this.objetivos = objetivos;
    }

    @Override
    protected boolean validarEspecifico() {
        return !objetivos.isEmpty();
    }

    @Override
    public String toString() {
        return "ReservaSpaResort{" +
                "objetivos=" + objetivos +
                '}';
    }
}
