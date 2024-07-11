package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.PreferenciaEventoCruzeiro;
import com.grupo11.hootel.entity.enums.TurnoEventoCruzeiro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EventoCruzeiro")
public class EventoCruzeiro extends Evento {

    @Enumerated(EnumType.STRING)
    @Column(name = "turno", nullable = false)
    @NotNull(message = "Todos os campos devem ser preenchidos")
    private TurnoEventoCruzeiro turno;

    @NotNull(message = "Todos os campos devem ser preenchidos")
    @Min(value = 0, message = "A idade minima é 0")
    @Column(name = "idade_minima")
    private Integer idadeMinimaRecomendada;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaEventoCruzeiro.class)
    @CollectionTable(name = "categorias_eventos_cruzeiro", joinColumns = @JoinColumn(name = "id_evento"))
    @Column(name = "categoria", nullable = false)
    @NotNull(message = "Todos os campos devem ser preenchidos")
    private List<PreferenciaEventoCruzeiro> categorias;

    public EventoCruzeiro() {
        super();
        idadeMinimaRecomendada = 0;
        turno = null;
        categorias = new ArrayList<>();
    }

    public EventoCruzeiro(Integer id, String horario, String lugar, String nome, LocalDate dataInicio, String descricao, List<Reserva> reservas, TurnoEventoCruzeiro turno, Integer idadeMinimaRecomendada, List<PreferenciaEventoCruzeiro> categorias) {
        super(id, horario, lugar, nome, dataInicio, descricao, reservas);
        this.turno = turno;
        this.idadeMinimaRecomendada = idadeMinimaRecomendada;
        this.categorias = categorias;
    }

    public TurnoEventoCruzeiro getTurno() {
        return turno;
    }

    public void setTurno(TurnoEventoCruzeiro turno) {
        this.turno = turno;
    }

    public Integer getIdadeMinimaRecomendada() {
        return idadeMinimaRecomendada;
    }

    public void setIdadeMinimaRecomendada(Integer idadeMinimaRecomendada) {
        this.idadeMinimaRecomendada = idadeMinimaRecomendada;
    }

    public List<PreferenciaEventoCruzeiro> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<PreferenciaEventoCruzeiro> categorias) {
        this.categorias = categorias;
    }

    @Override
    protected boolean validarEspecifico() {
        return turno != null && idadeMinimaRecomendada != null && idadeMinimaRecomendada >= 0 && categorias != null && !categorias.isEmpty();
    }

    @Override
    public String toString() {
        return "EventoCruzeiro{" +
                "turno=" + turno +
                ", idadeMinimaRecomendada=" + idadeMinimaRecomendada +
                ", categorias=" + categorias +
                '}';
    }
}
