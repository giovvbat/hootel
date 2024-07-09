package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.HorarioAgendamento;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AgendamentoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "reserva")
    @Valid
    private Reserva reserva;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "horario")
    private HorarioAgendamento horario;

    public AgendamentoServico() {
    }

    public AgendamentoServico(Integer id, Reserva reserva, HorarioAgendamento horario) {
        this.id = id;
        this.reserva = reserva;
        this.horario = horario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public HorarioAgendamento getHorario() {
        return horario;
    }

    public void setHorario(HorarioAgendamento horario) {
        this.horario = horario;
    }

    public boolean validar() {
        return reserva != null && horario != null && validarEspecifico();
    }

    protected abstract boolean validarEspecifico();

    @Override
    public String toString() {
        return "AgendamentoServico{" +
                "id=" + id +
                ", horario=" + horario +
                '}';
    }
}
