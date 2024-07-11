package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.PreferenciaAlimentarCruzeiro;
import com.grupo11.hootel.entity.enums.PreferenciaEventoCruzeiro;
import com.grupo11.hootel.entity.enums.TurnoEventoCruzeiro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ReservaCruzeiro")
public class ReservaCruzeiro extends Reserva {

    //@NotNull
    @Column(name = "idade")
    //@Min(value = 0, message = "A idade minima é 0")
    private Integer idade;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno")
    private TurnoEventoCruzeiro turnoPreferido;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaEventoCruzeiro.class)
    @CollectionTable(name = "preferencias_eventos_cruzeiro", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "preferencia_evento", nullable = false)
    @NotNull(message = "Selecione suas preferências de evento")
    private List<PreferenciaEventoCruzeiro> preferenciasEventos;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaAlimentarCruzeiro.class)
    @CollectionTable(name = "preferencias_alimentares_cruzeiro", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "preferencia_alimentar", nullable = false)
    @NotNull(message = "Selecione suas preferências de alimentação")
    private List<PreferenciaAlimentarCruzeiro> preferenciasAlimentares;

    public ReservaCruzeiro() {
        super();
        turnoPreferido = null;
        preferenciasAlimentares = new ArrayList<>();
        preferenciasEventos = new ArrayList<>();
    }

    public ReservaCruzeiro(Integer idade, TurnoEventoCruzeiro turnoPreferido, List<PreferenciaEventoCruzeiro> preferenciasEventos, List<PreferenciaAlimentarCruzeiro> preferenciasAlimentares) {
        super();
        this.idade = idade;
        this.turnoPreferido = turnoPreferido;
        this.preferenciasEventos = preferenciasEventos;
        this.preferenciasAlimentares = preferenciasAlimentares;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public TurnoEventoCruzeiro getTurnoPreferido() {
        return turnoPreferido;
    }

    public void setTurnoPreferido(TurnoEventoCruzeiro turnoPreferido) {
        this.turnoPreferido = turnoPreferido;
    }

    public List<PreferenciaEventoCruzeiro> getPreferenciasEventos() {
        return preferenciasEventos;
    }

    public void setPreferenciasEventos(List<PreferenciaEventoCruzeiro> preferenciasEventos) {
        this.preferenciasEventos = preferenciasEventos;
    }

    public List<PreferenciaAlimentarCruzeiro> getPreferenciasAlimentares() {
        return preferenciasAlimentares;
    }

    public void setPreferenciasAlimentares(List<PreferenciaAlimentarCruzeiro> preferenciasAlimentares) {
        this.preferenciasAlimentares = preferenciasAlimentares;
    }

    @Override
    protected boolean validarEspecifico() {
        return turnoPreferido != null && idade != null && idade >= 0 && preferenciasEventos != null && !preferenciasEventos.isEmpty() && preferenciasAlimentares != null && !preferenciasAlimentares.isEmpty();
    }

    @Override
    public String toString() {
        return "ReservaCruzeiro{" +
                "idade=" + idade +
                ", turnoPreferido=" + turnoPreferido +
                ", preferenciasEventos=" + preferenciasEventos +
                ", preferenciasAlimentares=" + preferenciasAlimentares +
                '}';
    }
}
