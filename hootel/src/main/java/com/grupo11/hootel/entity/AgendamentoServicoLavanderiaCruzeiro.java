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
    private EspecificacoesLavanderia especificacao;

    public AgendamentoServicoLavanderiaCruzeiro() {
        super();
    }

    public AgendamentoServicoLavanderiaCruzeiro(Integer id, Reserva reserva, HorarioAgendamento horario, EspecificacoesLavanderia especificacao) {
        super(id, reserva, horario);
        this.especificacao = especificacao;
    }

    public EspecificacoesLavanderia getEspecificacoes() {
        return especificacao;
    }

    public void setEspecificacoes(EspecificacoesLavanderia especificacao) {
        this.especificacao = especificacao;
    }

    @Override
    protected boolean validarEspecifico() {
        return especificacao != null;
    }

    @Override
    public String toString() {
        return "AgendamentoServicoLavanderiaCruzeiro{" +
                "especificacao=" + especificacao +
                '}';
    }
}
