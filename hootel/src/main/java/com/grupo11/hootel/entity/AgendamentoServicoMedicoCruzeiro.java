package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.HorarioAgendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "AgendamentoServicoMedicoCruzeiro")
public class AgendamentoServicoMedicoCruzeiro extends AgendamentoServico {

    @Column(name = "sintomas")
    @NotNull(message = "Informe os sintomas")
    private String sintomas;

    public AgendamentoServicoMedicoCruzeiro() {
        super();
    }

    public AgendamentoServicoMedicoCruzeiro(Integer id, Reserva reserva, HorarioAgendamento horario, String sintomas) {
        super(id, reserva, horario);
        this.sintomas = sintomas;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    @Override
    protected boolean validarEspecifico() {
        return !sintomas.isEmpty();
    }

    @Override
    public String toString() {
        return "AgendamentoServicoMedicoCruzeiro{" +
                "sintomas='" + sintomas + '\'' +
                '}';
    }
}