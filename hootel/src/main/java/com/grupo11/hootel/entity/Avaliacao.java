package com.grupo11.hootel.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "Avaliacao")
public class Avaliacao {

    @Id
    @OneToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @Column(name = "avalicacao_positiva")
    private String avalicacao_positiva;

    @Column(name = "avalicacao_negativa")
    private String avalicacao_negativa;

    @Column(name = "nota")
    @NotNull(message="is required")
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private String nota;

    @Column(name = "data_avaliacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date data;

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getAvalicacao_positiva() {
        return avalicacao_positiva;
    }

    public void setAvalicacao_positiva(String avalicacao_positiva) {
        this.avalicacao_positiva = avalicacao_positiva;
    }

    public String getAvalicacao_negativa() {
        return avalicacao_negativa;
    }

    public void setAvalicacao_negativa(String avalicacao_negativa) {
        this.avalicacao_negativa = avalicacao_negativa;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
