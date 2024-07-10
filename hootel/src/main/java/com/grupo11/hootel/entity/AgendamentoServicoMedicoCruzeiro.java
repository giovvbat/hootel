package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.EspecificacoesLavanderia;
import com.grupo11.hootel.entity.enums.EspecificacoesMedico;
import com.grupo11.hootel.entity.enums.HorarioAgendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AgendamentoServicoMedicoCruzeiro")
public class AgendamentoServicoMedicoCruzeiro extends AgendamentoServico {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = EspecificacoesMedico.class)
    @CollectionTable(name = "especificacoes_medico", joinColumns = @JoinColumn(name = "agendamento_medico"))
    @Column(name = "especificacao", nullable = false)
    @NotNull
    private List<EspecificacoesMedico> especificacoes;

    public AgendamentoServicoMedicoCruzeiro() {
        super();
        this.especificacoes = new ArrayList<>();
    }

    public AgendamentoServicoMedicoCruzeiro(Integer id, Reserva reserva, HorarioAgendamento horario, List<EspecificacoesMedico> especificacoes) {
        super(id, reserva, horario);
        this.especificacoes = especificacoes;
    }

    public List<EspecificacoesMedico> getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(List<EspecificacoesMedico> especificacoes) {
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