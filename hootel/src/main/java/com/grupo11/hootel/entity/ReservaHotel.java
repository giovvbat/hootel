package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.PreferenciaAlimentarHotel;
import com.grupo11.hootel.entity.enums.PreferenciaEventoHotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ReservaHotel")
public class ReservaHotel extends Reserva {

    @Column(name = "idade")
    private Integer idade;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaEventoHotel.class)
    @CollectionTable(name = "preferencias_eventos_hotel", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "preferencia_evento", nullable = false)
    @NotNull(message = "Selecione suas preferências de eventos")
    private List<PreferenciaEventoHotel> preferenciasEventos;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaAlimentarHotel.class)
    @CollectionTable(name = "preferencias_alimentares_hotel", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "preferencia_refeicao", nullable = false)
    @NotNull(message = "Selecione suas preferências de alimentação")
    private List<PreferenciaAlimentarHotel> preferenciasAlimentares;

    public ReservaHotel() {
        super();
        preferenciasEventos = new ArrayList<>();
        preferenciasAlimentares = new ArrayList<>();
        idade = 0;
    }

    public ReservaHotel(Integer idade, List<PreferenciaEventoHotel> preferenciasEventos, List<PreferenciaAlimentarHotel> preferenciasAlimentares) {
        super();
        this.idade = idade;
        this.preferenciasEventos = preferenciasEventos;
        this.preferenciasAlimentares = preferenciasAlimentares;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<PreferenciaEventoHotel> getPreferenciasEventos() {
        return preferenciasEventos;
    }

    public void setPreferenciasEventos(List<PreferenciaEventoHotel> preferenciasEventos) {
        this.preferenciasEventos = preferenciasEventos;
    }

    public List<PreferenciaAlimentarHotel> getPreferenciasAlimentares() {
        return preferenciasAlimentares;
    }

    public void setPreferenciasAlimentares(List<PreferenciaAlimentarHotel> preferenciasAlimentares) {
        this.preferenciasAlimentares = preferenciasAlimentares;
    }

    @Override
    protected boolean validarEspecifico() {
        return idade >= 0 &&
                !preferenciasAlimentares.isEmpty() &&
                !preferenciasEventos.isEmpty();
    }

    @Override
    public String toString() {
        return "ReservaHotel{" +
                "idade=" + idade +
                ", preferenciasEventos=" + preferenciasEventos +
                ", preferenciasAlimentares='" + preferenciasAlimentares + '\'' +
                '}';
    }
}
