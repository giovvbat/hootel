package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.PreferenciaAlimentarCruzeiro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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

    @NotNull(message = "Todos os campos devem ser preenchidos")
    @Min(value = 0, message = "A idade minima é 0")
    @Column(name = "idade_minima")
    private Integer idadeMinimaRecomendada;

    public RefeicaoCruzeiro() {
        super();
        this.categorias = new ArrayList<>();
        this.idadeMinimaRecomendada = 0;
    }

    public RefeicaoCruzeiro(Integer id, String conteudo, List<PreferenciaAlimentarCruzeiro> categorias, Integer idadeMinimaRecomendada) {
        super(id, conteudo);
        this.categorias = categorias;
        this.idadeMinimaRecomendada = idadeMinimaRecomendada;
    }

    public List<PreferenciaAlimentarCruzeiro> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<PreferenciaAlimentarCruzeiro> categorias) {
        this.categorias = categorias;
    }

    public Integer getIdadeMinimaRecomendada() {
        return idadeMinimaRecomendada;
    }

    public void setIdadeMinimaRecomendada(Integer idadeMinimaRecomendada) {
        this.idadeMinimaRecomendada = idadeMinimaRecomendada;
    }

    @Override
    protected boolean validarEspecifico() {
        return !categorias.isEmpty() && idadeMinimaRecomendada != null && idadeMinimaRecomendada >= 0;
    }

    @Override
    public String toString() {
        return "RefeicaoCruzeiro{" +
                "categorias=" + categorias +
                ", idadeMinimaRecomendada=" + idadeMinimaRecomendada +
                '}';
    }
}
