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

    // adicionarParticipante tbm tinha como parametro  ->int numParticipantes<- mas tirei por enquanto
    @Override
    public void adicionarParticipante(Long pinReserva, int idEvento) {
        Evento evento = lerEventoId(idEvento);
        Reserva reserva = reservaService.lerReservaPin(pinReserva);

        if (!evento.getReservas().contains(reserva)) {
            evento.addReserva(reserva);
            eventoRepository.save(evento);
            System.out.println("aqui-1");
        }else {
            //fazer alguma coisa, pois está tentando reserva presença para o mesmo evento"
            System.out.println("aqui-2");
        }
    }
}
