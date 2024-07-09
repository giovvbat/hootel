package com.grupo11.hootel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "EventoCruzeiro")
public class EventoCruzeiro extends Evento {

    @Override
    protected boolean validarEspecifico() {
        return false;
    }
}
