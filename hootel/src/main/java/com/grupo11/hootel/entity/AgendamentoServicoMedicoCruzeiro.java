package com.grupo11.hootel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "AgendamentoServicoMedicoCruzeiro")
public class AgendamentoServicoMedicoCruzeiro extends AgendamentoServico {

    @Override
    protected boolean validarEspecifico() {
        return false;
    }
}
