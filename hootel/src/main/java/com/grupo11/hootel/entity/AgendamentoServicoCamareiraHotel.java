package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.EspecificacoesCamareira;
import com.grupo11.hootel.entity.enums.HorarioAgendamento;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "AgendamentoCamareira")
public class AgendamentoServicoCamareiraHotel extends AgendamentoServico {


    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = EspecificacoesCamareira.class)
    @CollectionTable(name = "especificacoes_camareira", joinColumns = @JoinColumn(name = "agendamento_camareira"))
    @Column(name = "especificacao", nullable = false)
    @NotNull(message = "Escolha uma tarefa")
    private List<EspecificacoesCamareira> especificacoes;

    public AgendamentoServicoCamareiraHotel() {
        super();
        especificacoes = new ArrayList<>();
    }

    public AgendamentoServicoCamareiraHotel(Integer id, HorarioAgendamento horario, List<EspecificacoesCamareira> especificacoes, ReservaHotel reserva) {
        super(id, reserva, horario);
        this.especificacoes = especificacoes;
    }

    public List<EspecificacoesCamareira> getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(List<EspecificacoesCamareira> especificacoes) {
        this.especificacoes = especificacoes;
    }

    @Override
    protected boolean validarEspecifico() {
        return !especificacoes.isEmpty();
    }

    @Override
    public String toString() {
        return "AgendamentoServicoCamareiraHotel{" +
                ", especificacoes=" + especificacoes +
                '}';
    }
}
