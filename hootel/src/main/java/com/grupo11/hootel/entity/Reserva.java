package com.grupo11.hootel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reserva")
public class Reserva {

    @Id
    @Column(name="pin")
    private long PIN;

    public Reserva() {
    }

    public Reserva(long PIN) {
        this.PIN = PIN;
    }

    public long getPIN() {
        return PIN;
    }

    public void setPIN(long PIN) {
        this.PIN = PIN;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "PIN=" + PIN +
                '}';
    }
}
