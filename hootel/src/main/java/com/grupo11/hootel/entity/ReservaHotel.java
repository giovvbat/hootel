package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.PreferenciaAlimentar;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ReservaHotel")
public class ReservaHotel extends Reserva {


    @NotNull
    @Column(name = "idade")
    @Min(value = 0, message = "A idade minima é 0")
    private Integer idade;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "preferencias_eventos", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "preferencia_evento", nullable = false)
    @NotNull(message = "Selecione suas preferências de eventos")
    private List<String> preferenciasEventos;

    @Column(name = "preferencia_alimentacao", nullable = false)
    @NotNull(message = "Selecione suas preferências de alimentação")
    private PreferenciaAlimentar preferenciasAlimentacao;

    public ReservaHotel() {
        super();
        preferenciasEventos = new ArrayList<>();
    }

    public ReservaHotel(Integer idade, List<String> preferenciasEventos, String preferenciasAlimentacao) {
        super();
        this.idade = idade;
        this.preferenciasEventos = preferenciasEventos;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<String> getPreferenciasEventos() {
        return preferenciasEventos;
    }

    public void setPreferenciasEventos(List<String> preferenciasEventos) {
        this.preferenciasEventos = preferenciasEventos;
    }

    public PreferenciaAlimentar getPreferenciasAlimentacao() {
        return preferenciasAlimentacao;
    }

    public void setPreferenciasAlimentacao(PreferenciaAlimentar preferenciasAlimentacao) {
        this.preferenciasAlimentacao = preferenciasAlimentacao;
    }

    @Override
    public String toString() {
        return "ReservaHotel{" +
                "idade=" + idade +
                ", preferenciasEventos=" + preferenciasEventos +
                ", preferenciasAlimentacao='" + preferenciasAlimentacao + '\'' +
                '}';
    }
}
