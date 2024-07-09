package com.grupo11.hootel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "AgendamentoServicoLavanderiaCruzeiro")
public class AgendamentoServicoLavanderiaCruzeiro extends AgendamentoServico {

    @Override
    protected boolean validarEspecifico() {
        return false;
    }
}
