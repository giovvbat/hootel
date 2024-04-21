package com.grupo11.hootel.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "horario_camareira")
public class HorarioCamareira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "horario")
    private Date horario;

    @OneToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    public HorarioCamareira() {
    }

    public HorarioCamareira(int id, Date horario, Reserva reserva) {
        this.id = id;
        this.horario = horario;
        this.reserva = reserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "HorarioCamareira{" +
                "id=" + id +
                ", horario=" + horario +
                ", reserva=" + reserva +
                '}';
    }
}