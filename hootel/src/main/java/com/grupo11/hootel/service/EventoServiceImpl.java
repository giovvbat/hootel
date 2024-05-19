package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.EventoRepository;
import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    private final ReservaService reservaService;
    private final EventoRepository eventoRepository;

    @Autowired
    public EventoServiceImpl(EventoRepository eventoRepository, ReservaService reservaService) {
        this.eventoRepository = eventoRepository;
        this.reservaService = reservaService;
    }

    @Override
    @Transactional
    public void atualizarEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    @Override
    @Transactional
    public void criarEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    @Override
    public Evento lerEventoId(Integer id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento inválido"));
    }

    @Override
    public List<Evento> lerTodosEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public void adicionarParticipante(Long pinReserva, int idEvento) {
        Evento evento = lerEventoId(idEvento);
        Reserva reserva = reservaService.lerReservaPin(pinReserva);

        if (!evento.getReservas().contains(reserva)) {
            evento.addReserva(reserva);
            eventoRepository.save(evento);
        }else {
            throw new RuntimeException("Você já confirmou presença nesse evento");
        }
    }

    @Override
    public void removerParticipante(Long pinReserva, int idEvento) {
        Evento evento = lerEventoId(idEvento);
        Reserva reserva = reservaService.lerReservaPin(pinReserva);

        if (evento.getReservas().contains(reserva)) {
            evento.removeReserva(reserva);
            eventoRepository.save(evento);
        }else {
            throw new RuntimeException("Sua presença não está confirmada para esse evento");
        }
    }

}
