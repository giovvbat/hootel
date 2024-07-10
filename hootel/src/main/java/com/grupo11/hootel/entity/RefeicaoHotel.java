package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.PreferenciaAlimentarHotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RefeicaoHotel")
public class RefeicaoHotel extends Refeicao {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaAlimentarHotel.class)
    @CollectionTable(name = "categorias_refeicao_hotel", joinColumns = @JoinColumn(name = "id_refeicao"))
    @Column(name = "categoria", nullable = false)
    @NotNull(message = "Todos os campos devem ser preenchidos")
    private List<PreferenciaAlimentarHotel> categorias;

    public RefeicaoHotel() {
        super();
        this.categorias = new ArrayList<>();
    }

    public RefeicaoHotel(Integer id, String conteudo, List<PreferenciaAlimentarHotel> categorias) {
        super(id, conteudo);
        this.categorias = categorias;
    }

    public List<PreferenciaAlimentarHotel> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<PreferenciaAlimentarHotel> categorias) {
        this.categorias = categorias;
    }

    protected boolean validarEspecifico() {
        return !categorias.isEmpty();
    }

    @Override
    public String toString() {
        return "RefeicaoHotel{" +
                "categorias=" + categorias +
                '}';
    }
}
