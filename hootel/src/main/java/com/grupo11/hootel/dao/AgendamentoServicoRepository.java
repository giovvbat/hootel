package com.grupo11.hootel.dao;

import com.grupo11.hootel.entity.AgendamentoServico;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.entity.enums.HorarioAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoServicoRepository extends JpaRepository<AgendamentoServico, Integer> {
    public List<AgendamentoServico> findAllByReserva(Reserva reserva);
    public List<AgendamentoServico> findAllByHorario(HorarioAgendamento horario);
}
