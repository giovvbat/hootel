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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao")
    private Integer id_avaliacao;

    @Column(name = "avaliacao_positiva")
    private String avaliacao_positiva;

    @Column(name = "avaliacao_negativa")
    private String avaliacao_negativa;

    @Column(name = "nota")
    @NotNull(message="is required")
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private Integer nota;

    @Column(name = "data_avaliacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date data;

    public Avaliacao() {

    }

    public Avaliacao(Integer id_avaliacao, String avaliacao_positiva, String avaliacao_negativa, Integer nota, Date data) {
        this.id_avaliacao = id_avaliacao;
        this.avaliacao_positiva = avaliacao_positiva;
        this.avaliacao_negativa = avaliacao_negativa;
        this.nota = nota;
        this.data = data;
    }

    public int getId_avaliacao() {
        return id_avaliacao;
    }

    public void setId_avaliacao(int id_avaliacao) {
        this.id_avaliacao = id_avaliacao;
    }

    public String getavaliacao_positiva() {
        return avaliacao_positiva;
    }

    public void setavaliacao_positiva(String avaliacao_positiva) {
        this.avaliacao_positiva = avaliacao_positiva;
    }

    public String getavaliacao_negativa() {
        return avaliacao_negativa;
    }

    public void setavaliacao_negativa(String avaliacao_negativa) {
        this.avaliacao_negativa = avaliacao_negativa;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                ", avaliacao_positiva='" + avaliacao_positiva + '\'' +
                ", avaliacao_negativa='" + avaliacao_negativa + '\'' +
                ", nota='" + nota + '\'' +
                ", data=" + data +
                '}';
    }
}
