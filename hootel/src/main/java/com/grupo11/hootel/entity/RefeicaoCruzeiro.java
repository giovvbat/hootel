package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.PreferenciaAlimentarCruzeiro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RefeicaoCruzeiro")
public class RefeicaoCruzeiro extends Refeicao {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaAlimentarCruzeiro.class)
    @CollectionTable(name = "categorias_refeicao_cruzeiro", joinColumns = @JoinColumn(name = "id_refeicao"))
    @Column(name = "categoria", nullable = false)
    @NotNull(message = "Todos os campos devem ser preenchidos")
    private List<PreferenciaAlimentarCruzeiro> categorias;

    @Column(name = "restaurante")
    @NotNull(message = "Todos os campos devem ser preenchidos")
    @NotEmpty(message = "Todos os campos devem ser preenchidos")
    private String restaurante;

    public RefeicaoCruzeiro() {
        super();
        this.categorias = new ArrayList<>();
        this.restaurante = "";
    }

    public RefeicaoCruzeiro(Integer id, String conteudo, List<PreferenciaAlimentarCruzeiro> categorias, String restaurante) {
        super(id, conteudo);
        this.categorias = categorias;
        this.restaurante = restaurante;
    }

    public List<PreferenciaAlimentarCruzeiro> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<PreferenciaAlimentarCruzeiro> categorias) {
        this.categorias = categorias;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    protected boolean validarEspecifico() {
        return !categorias.isEmpty() && restaurante != null && !restaurante.isEmpty();
    }

    @Override
    public String toString() {
        return "RefeicaoCruzeiro{" +
                "categorias=" + categorias +
                ", restaurante='" + restaurante + '\'' +
                '}';
    }
}
