package com.grupo11.hootel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "alimentacao")
public class Alimentacao {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "conteudo")
    @NotNull(message = "Todos os campos devem ser preenchidos")
    @NotEmpty(message = "Todos os campos devem ser preenchidos")
    private String conteudo;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "categorias_alimentacao", joinColumns = @JoinColumn(name = "id_alimentacao"))
    @Column(name = "categoria", nullable = false)
    @NotNull(message = "Todos os campos devem ser preenchidos")
    private List<String> categorias;

    public Alimentacao() {
    }

    public Alimentacao(Integer id, String conteudo, List<String> categorias) {
        this.id = id;
        this.conteudo = conteudo;
        this.categorias = categorias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "Todos os campos devem ser preenchidos") @NotEmpty(message = "Todos os campos devem ser preenchidos") String getConteudo() {
        return conteudo;
    }

    public void setConteudo(@NotNull(message = "Todos os campos devem ser preenchidos") @NotEmpty(message = "Todos os campos devem ser preenchidos") String conteudo) {
        this.conteudo = conteudo;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Cardapio{" +
                "id=" + id +
                ", conteudo='" + conteudo + '\'' +
                ", categorias=" + categorias +
                '}';
    }
}
