package com.grupo11.hootel.service;

import com.grupo11.hootel.entity.AgendamentoServico;

import java.util.List;

public interface AgendamentoServicoService {

    List<AgendamentoServico> lerAgendamentos(Class<? extends AgendamentoServico> type);

    void criarAgendamento(AgendamentoServico agendamento);

}
