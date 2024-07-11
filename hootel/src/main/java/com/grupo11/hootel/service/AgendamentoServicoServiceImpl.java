package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.AgendamentoServicoRepository;
import com.grupo11.hootel.entity.AgendamentoServico;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.AgendamentosIndisponiveisException;
import com.grupo11.hootel.exceptions.AgendamentoInvalidoException;
import com.grupo11.hootel.exceptions.ServicoSolicitadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoServicoServiceImpl implements AgendamentoServicoService {

    private final AgendamentoServicoRepository agendamentoRepository;
    private final ReservaService reservaService;

    @Autowired
    public AgendamentoServicoServiceImpl(AgendamentoServicoRepository agendamentoRepository, ReservaService reservaService) {
        this.agendamentoRepository = agendamentoRepository;
        this.reservaService = reservaService;
    }

    @Override
    public List<AgendamentoServico> lerAgendamentos(Class<? extends AgendamentoServico> type) {
        List<AgendamentoServico> agendamentos = agendamentoRepository.findAll()
                .stream().filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());

        if (agendamentos.isEmpty()) {
            throw new AgendamentosIndisponiveisException();
        }

        return agendamentos;
    }

    @Override
    public void criarAgendamento(AgendamentoServico agendamento) {
        Reserva reserva = reservaService.lerReservaPin(agendamento.getReserva().getPIN());

        if (!agendamento.validar()) {
            throw new AgendamentoInvalidoException();
        }

        agendamentoRepository.save(agendamento);
    }
}
