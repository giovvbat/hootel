package com.grupo11.hootel.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "horario_camareira")
public class HorarioCamareira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(message = "Selecione um horário!")
    private Integer id;

    @Column(name = "horario")
    private Date horario;

    @OneToOne
    @JoinColumn(name = "id_reserva")
    @Valid
    private Reserva reserva;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "servicos", joinColumns = @JoinColumn(name = "id_servico"))
    @Column(name = "servico", nullable = false)
    @NotNull
    private List<String> servicos;

    public HorarioCamareira() {
    }

    public HorarioCamareira(Integer id, Date horario, Reserva reserva) {
        this.id = id;
        this.horario = horario;
        this.reserva = reserva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<String> getServicos() {
        return servicos;
    }

    public void setServicos(List<String> servicos) {
        this.servicos = servicos;
    }

    @Override
    public String toString() {
        DateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(horario);
    }
}