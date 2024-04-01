package com.grupo11.hootel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cardapio")
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bebidas")
    private String bebidas;

    @Column(name = "opcao_vegetariana")
    private String opcaoVegetariana;

    @Column(name = "opcao_carnivora")
    private String opcaoCarnivora;

    public Cardapio() {

    }

    public Cardapio(Integer id, String bebidas, String opcaoVegetariana, String opcaoCarnivora) {
        this.id = id;
        this.bebidas = bebidas;
        this.opcaoVegetariana = opcaoVegetariana;
        this.opcaoCarnivora = opcaoCarnivora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBebidas() {
        return bebidas;
    }

    public void setBebidas(String bebidas) {
        this.bebidas = bebidas;
    }

    public String getOpcaoVegetariana() {
        return opcaoVegetariana;
    }

    public void setOpcaoVegetariana(String opcaoVegetariana) {
        this.opcaoVegetariana = opcaoVegetariana;
    }

    public String getOpcaoCarnivora() {
        return opcaoCarnivora;
    }

    public void setOpcaoCarnivora(String opcaoCarnivora) {
        this.opcaoCarnivora = opcaoCarnivora;
    }

    @Override
    public String toString() {
        return "Cardapio{" +
                "id=" + id +
                ", bebidas='" + bebidas + '\'' +
                ", opcaoVegetariana='" + opcaoVegetariana + '\'' +
                ", opcaoCarnivora='" + opcaoCarnivora + '\'' +
                '}';
    }
}
