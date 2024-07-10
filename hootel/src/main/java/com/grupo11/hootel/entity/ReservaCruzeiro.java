package com.grupo11.hootel.entity;

import com.grupo11.hootel.entity.enums.PreferenciaAlimentarCruzeiro;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ReservaCruzeiro")
public class ReservaCruzeiro extends Reserva {

    // @NotNull
    @Column(name = "idade")
    // @Min(value = 0, message = "A idade minima é 0")
    private Integer idade;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PreferenciaAlimentarCruzeiro.class)
    @CollectionTable(name = "preferencias_alimentares_cruzeiro", joinColumns = @JoinColumn(name = "pin_reserva"))
    @Column(name = "preferencia_refeicao", nullable = false)
    // @NotNull(message = "Selecione suas preferências de alimentação")
    private List<PreferenciaAlimentarCruzeiro> preferenciasAlimentares;

    @Override
    protected boolean validarEspecifico() {
        return false;
    }
}
