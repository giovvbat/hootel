package com.grupo11.hootel.service;

import com.grupo11.hootel.dao.EventoRepository;
import com.grupo11.hootel.entity.Evento;
import com.grupo11.hootel.entity.Reserva;
import com.grupo11.hootel.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    //colocar excecoes pro caso do evento nao existir
    public void atualizarEvento(Evento evento) {

        eventoRepository.save(evento);
    }


    @Override
    @Transactional
    public void criarEvento(Evento evento) {

        Optional<Evento> optionalEvento = eventoRepository.findById(evento.getId());
        if(evento.getNome() == null || evento.getHorario() == null ||
        evento.getLugar() == null || evento.getDataInicio() == null ||
        evento.getDescricao() == null) {
            throw new EventoIncompletoException();
        }

        eventoRepository.save(evento);
    }

    @Override
    public Evento lerEventoId(Integer id) {
        return eventoRepository.findById(id)
                .orElseThrow(EventoInvalidoException::new);
    }

    //colocar excecao pro caso de nenhum evento existir
    @Override
    public List<Evento> lerTodosEventos() {

        return eventoRepository.findAll();
    }

    @Override
    public void deletarEvento(Evento evento) {

    }

    @Override
    public void adicionarParticipante(Long pinReserva, int idEvento) {
        Evento evento = lerEventoId(idEvento);
        Reserva reserva = reservaService.lerReservaPin(pinReserva);

        if (!evento.getReservas().contains(reserva)) {
            evento.addReserva(reserva);
            eventoRepository.save(evento);
        }else {
            throw new EventoConfirmadoException();
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
            throw new EventoNaoConfirmadoException();
        }
    }

}
