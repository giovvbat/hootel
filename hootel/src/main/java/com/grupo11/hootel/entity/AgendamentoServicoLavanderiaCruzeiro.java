package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.EspecificacoesLavanderia;
import com.grupo11.hootel.entity.enums.HorarioAgendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AgendamentoServicoLavanderiaCruzeiro")
public class AgendamentoServicoLavanderiaCruzeiro extends AgendamentoServico {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = EspecificacoesLavanderia.class)
    @CollectionTable(name = "especificacoes_lavanderia", joinColumns = @JoinColumn(name = "agendamento_lavanderia"))
    @Column(name = "especificacao", nullable = false)
    @NotNull
    private List<EspecificacoesLavanderia> especificacoes;

    public AgendamentoServicoLavanderiaCruzeiro() {
        super();
        this.especificacoes = new ArrayList<>();
    }

    public AgendamentoServicoLavanderiaCruzeiro(Integer id, Reserva reserva, HorarioAgendamento horario, List<EspecificacoesLavanderia> especificacoes) {
        super(id, reserva, horario);
        this.especificacoes = especificacoes;
    }

    public List<EspecificacoesLavanderia> getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(List<EspecificacoesLavanderia> especificacoes) {
        this.especificacoes = especificacoes;
    }

    @Override
    protected boolean validarEspecifico() {
        return !especificacoes.isEmpty();
    }

    @Override
    public String toString() {
        return "AgendamentoServicoLavanderiaCruzeiro{" +
                "especificacoes=" + especificacoes +
                '}';
    }
}
