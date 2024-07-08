package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.ObjetivosSpaResort;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarSpaResort;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AlimentacaoSpaResort")
public class AlimentacaoSpaResort extends Alimentacao {

    @Enumerated
    @ElementCollection(targetClass = PreferenciaAlimentarSpaResort.class)
    @CollectionTable(name = "categorias_alimentacao", joinColumns = @JoinColumn(name = "id_alimentacao"))
    @Column(name = "categoria", nullable = false)
    @NotNull(message = "Todos os campos devem ser preenchidos")
    private List<PreferenciaAlimentarSpaResort> categorias;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = ObjetivosSpaResort.class)
    @CollectionTable(name = "objetivos", joinColumns = @JoinColumn(name = "id_alimentacao"))
    @Column(name = "objetivo", nullable = false)
    @NotNull(message = "Selecione seus objetivos")
    private List<ObjetivosSpaResort> objetivos;

    public AlimentacaoSpaResort() {
        super();
        this.categorias = new ArrayList<>();
    }

    public AlimentacaoSpaResort(Integer id, String conteudo, List<PreferenciaAlimentarSpaResort> categorias) {
        super(id, conteudo);
        this.categorias = categorias;
    }

    public List<PreferenciaAlimentarSpaResort> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<PreferenciaAlimentarSpaResort> categorias) {
        this.categorias = categorias;
    }

    public List<ObjetivosSpaResort> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivosSpaResort> objetivos) {
        this.objetivos = objetivos;
    }

    @Override
    protected boolean validarEspecifico() {
        return !categorias.isEmpty() && !objetivos.isEmpty();
    }

    @Override
    public String toString() {
        return "AlimentacaoSpaResort{" +
                "categorias=" + categorias +
                '}';
    }
}
