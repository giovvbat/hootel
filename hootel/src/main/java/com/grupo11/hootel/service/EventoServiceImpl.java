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

    public void atualizarEvento(Evento evento) {

        Optional<Evento> optionalEvento = eventoRepository.findById(evento.getId());
        if(optionalEvento.isEmpty()) {
            throw new EventoInvalidoException();
        }

        eventoRepository.save(evento);
    }


    @Override
    @Transactional
    public void criarEvento(Evento evento) {

        //Optional<Evento> optionalEvento = eventoRepository.findById(evento.getId());
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

    @Override
    public List<Evento> lerTodosEventos() {

        List<Evento> eventos = eventoRepository.findAll();
        if(eventos.isEmpty()) {
            throw new NenhumEventoException();
        }

        return eventos;
    }

    @Override
    public void deletarEvento(Integer idEvento) {

        Optional<Evento> optionalEvento = eventoRepository.findById(idEvento);
        if(optionalEvento.isEmpty()) {
            throw new EventoInvalidoException();
        }

        eventoRepository.delete(optionalEvento.get());
    }

    @Override
    public int quantidadeParticipantes(Evento evento){
        return evento.quantidadeReservas();
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
