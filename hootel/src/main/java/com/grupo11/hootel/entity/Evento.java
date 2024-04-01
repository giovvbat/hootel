package com.grupo11.hootel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "horario")
    private String horario;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @Column(name = "capacidade")
    private Integer capacidade;

    @Column(name = "ocupacao")
    private Integer ocupacao;

    public Evento() { }

    public Evento(Integer id, String horario, String lugar, String nome, Date dataInicio, Integer capacidade, Integer ocupacao) {
        this.id = id;
        this.horario = horario;
        this.lugar = lugar;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.capacidade = capacidade;
        this.ocupacao = ocupacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Integer getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Integer ocupacao) {
        this.ocupacao = ocupacao;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", horario='" + horario + '\'' +
                ", lugar='" + lugar + '\'' +
                ", nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", capacidade=" + capacidade +
                ", ocupacao=" + ocupacao +
                '}';
    }
}
