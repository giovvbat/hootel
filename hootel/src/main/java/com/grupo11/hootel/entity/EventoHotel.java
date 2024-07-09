package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.PreferenciaEventoHotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EventoHotel")
public class EventoHotel extends Evento {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaEventoHotel.class)
    @CollectionTable(name = "categorias_eventos_hotel", joinColumns = @JoinColumn(name = "id_evento"))
    @Column(name = "categoria", nullable = false)
    @NotNull(message = "Todos os campos devem ser preenchidos")
    private List<PreferenciaEventoHotel> categorias;

    @NotNull(message = "Todos os campos devem ser preenchidos")
    @Min(value = 0, message = "A idade minima é 0")
    @Column(name = "idade_minima")
    private Integer idadeMinimaRecomendada;

    @Override
    protected boolean validarEspecifico() {
        return !categorias.isEmpty() && idadeMinimaRecomendada != null && idadeMinimaRecomendada >= 0;
    }

    public EventoHotel() {
        super();
        this.categorias = new ArrayList<>();
        this.idadeMinimaRecomendada = 0;
    }

    public EventoHotel(Integer id, String horario, String lugar, String nome, LocalDate dataInicio, String descricao, List<Reserva> reservas, List<PreferenciaEventoHotel> categorias, Integer idadeMinimaRecomendada) {
        super(id, horario, lugar, nome, dataInicio, descricao, reservas);
        this.categorias = categorias;
        this.idadeMinimaRecomendada = idadeMinimaRecomendada;
    }

    public List<PreferenciaEventoHotel> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<PreferenciaEventoHotel> categorias) {
        this.categorias = categorias;
    }

    public Integer getIdadeMinimaRecomendada() {
        return idadeMinimaRecomendada;
    }

    public void setIdadeMinimaRecomendada(Integer idadeMinimaRecomendada) {
        this.idadeMinimaRecomendada = idadeMinimaRecomendada;
    }

    @Override
    public String toString() {
        return "EventoHotel{" +
                ", categorias=" + categorias +
                ", idadeMinimaRecomendada=" + idadeMinimaRecomendada +
                '}';
    }
}
