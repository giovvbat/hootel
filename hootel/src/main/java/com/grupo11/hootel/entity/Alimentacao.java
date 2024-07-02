package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.PreferenciaAlimentarHotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Alimentacao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "conteudo")
    @NotNull(message = "Todos os campos devem ser preenchidos")
    @NotEmpty(message = "Todos os campos devem ser preenchidos")
    private String conteudo;

    public Alimentacao() {
    }

    public Alimentacao(Integer id, String conteudo) {
        this.id = id;
        this.conteudo = conteudo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public abstract boolean validar();

    @Override
    public String toString() {
        return "Cardapio{" +
                "id=" + id +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }
}
