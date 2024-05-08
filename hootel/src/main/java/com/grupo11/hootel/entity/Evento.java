package com.grupo11.hootel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="evento")
public class Evento {


    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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

    @ManyToMany
    @JoinTable(
            name = "participantes_eventos",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "reserva_pin")
    )
    private List<Reserva> reservas;

    @Column(name = "descricao")
    private String descricao;

    public Evento() { }

    public Evento(Integer id, String horario, String lugar, String nome, Date dataInicio, String descricao) {
        this.id = id;
        this.horario = horario;
        this.lugar = lugar;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.descricao = descricao;
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

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void addReserva(Reserva reserva) {
        if (reservas == null) {
            reservas = new ArrayList<>();
        }

        reservas.add(reserva);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", horario='" + horario + '\'' +
                ", lugar='" + lugar + '\'' +
                ", nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", reservas=" + reservas +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
