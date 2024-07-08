package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.EspecificacoesCamareira;
import com.grupo11.hootel.entity.enums.EspecificacoesRelaxamento;
import com.grupo11.hootel.entity.enums.HorarioAgendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AgendamentoRelaxamento")
public class AgendamentoServicoRelaxamentoSpaResort extends AgendamentoServico {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = EspecificacoesRelaxamento.class)
    @CollectionTable(name = "especificacoes", joinColumns = @JoinColumn(name = "agendamento_relaxamento"))
    @Column(name = "especificacao", nullable = false)
    @NotNull
    private List<EspecificacoesRelaxamento> especificacoes;

    public AgendamentoServicoRelaxamentoSpaResort() {
        super();
        especificacoes = new ArrayList<>();
    }

    public AgendamentoServicoRelaxamentoSpaResort(Integer id, HorarioAgendamento horario, List<EspecificacoesRelaxamento> especificacoes, ReservaSpaResort reserva) {
        super(id, reserva, horario);
        this.especificacoes = especificacoes;
    }

    public List<EspecificacoesRelaxamento> getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(List<EspecificacoesRelaxamento> especificacoes) {
        this.especificacoes = especificacoes;
    }

    @Override
    protected boolean validarEspecifico() {
        return !especificacoes.isEmpty();
    }

    @Override
    public String toString() {
        return "AgendamentoServicoRelaxamentoSpaResort{" +
                "especificacoes=" + especificacoes +
                '}';
    }
}
