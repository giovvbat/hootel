package com.grupo11.hootel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name="evento")
public class Evento {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Todos os campos devem ser preenchidos")
    @NotEmpty(message = "Todos os campos devem ser preenchidos")
    @Column(name = "horario")
    private String horario;

    @NotNull(message = "Todos os campos devem ser preenchidos")
    @NotEmpty(message = "Todos os campos devem ser preenchidos")
    @Column(name = "lugar")
    private String lugar;

    @NotNull(message = "Todos os campos devem ser preenchidos")
    @NotEmpty(message = "Todos os campos devem ser preenchidos")
    @Column(name = "nome")
    private String nome;

    @NotNull(message = "Todos os campos devem ser preenchidos")
    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @ManyToMany
    @JoinTable(
            name = "participantes_eventos",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "reserva_pin")
    )
    private List<ReservaHotel> reservas;

    @NotNull(message = "Todos os campos devem ser preenchidos")
    @NotEmpty(message = "Todos os campos devem ser preenchidos")
    @Column(name = "descricao")
    private String descricao;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "categorias_eventos", joinColumns = @JoinColumn(name = "id_evento"))
    @Column(name = "categoria", nullable = false)
    @NotNull(message = "Todos os campos devem ser preenchidos")
    private List<String> categorias;

    @NotNull(message = "Todos os campos devem ser preenchidos")
    @Column(name = "idade_minima")
    private Integer idadeMinimaRecomendada;

    public Evento() { }

    public Evento(Integer id, String horario, String lugar, String nome, LocalDate dataInicio, String descricao,
                  List<String> categorias, Integer idadeMinimaRecomendada) {
        this.id = id;
        this.horario = horario;
        this.lugar = lugar;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.descricao = descricao;
        this.categorias = categorias;
        this.idadeMinimaRecomendada = idadeMinimaRecomendada;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public List<ReservaHotel> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaHotel> reservas) {
        this.reservas = reservas;
    }

    public void addReserva(ReservaHotel reserva) {
        if (reservas == null) {
            reservas = new ArrayList<>();
        }

        reservas.add(reserva);
    }

    public void removeReserva(Reserva reserva) {
        if (reservas != null) {
            reservas.remove(reserva);
        }
    }

    public int quantidadeReservas(){
        if (reservas == null) {
            reservas = new ArrayList<>();
        }
        return reservas.size();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "Todos os campos devem ser preenchidos") List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(@NotNull(message = "Todos os campos devem ser preenchidos") List<String> categorias) {
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
        return "Evento{" +
                "id=" + id +
                ", horario='" + horario + '\'' +
                ", lugar='" + lugar + '\'' +
                ", nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", reservas=" + reservas +
                ", descricao='" + descricao + '\'' +
                ", categorias=" + categorias +
                ", idadeMinimaRecomendada=" + idadeMinimaRecomendada +
                '}';
    }
}
