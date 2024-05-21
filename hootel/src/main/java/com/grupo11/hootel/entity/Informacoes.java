package com.grupo11.hootel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Informacoes")
public class Informacoes {

    @Id
    @Column(name = "id_informacoes")
    private Integer id;

    @Column(name = "nome_wifi")
    @NotNull
    @NotEmpty
    private String nomeWifi;

    @Column(name = "senha_wifi")
    @NotNull
    @NotEmpty
    private String senhaWifi;

    @Column(name = "numero_recepcao")
    @NotNull
    @NotEmpty
    private String numeroRecepcao;

    @Column(name = "horario_inicio_piscina")
    @NotNull
    @NotEmpty
    private String inicioPiscina;

    @Column(name = "horario_final_piscina")
    @NotNull
    @NotEmpty
    private String finalPiscina;

    @Column(name = "horario_inicio_cafe")
    @NotNull
    @NotEmpty
    private String inicioCafe;

    @Column(name = "horario_final_cafe")
    @NotNull
    @NotEmpty
    private String finalCafe;

    public Informacoes() {
    }

    public Informacoes(Integer id, String nomeWifi, String senhaWifi, String numeroRecepcao, String inicioPiscina,
                       String finalPiscina, String inicioCafe, String finalCafe) {
        this.id = id;
        this.nomeWifi = nomeWifi;
        this.senhaWifi = senhaWifi;
        this.numeroRecepcao = numeroRecepcao;
        this.inicioPiscina = inicioPiscina;
        this.finalPiscina = finalPiscina;
        this.inicioCafe = inicioCafe;
        this.finalCafe = finalCafe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeWifi() {
        return nomeWifi;
    }

    public void setNomeWifi(String nomeWifi) {
        this.nomeWifi = nomeWifi;
    }

    public String getSenhaWifi() {
        return senhaWifi;
    }

    public void setSenhaWifi(String senhaWifi) {
        this.senhaWifi = senhaWifi;
    }

    public String getNumeroRecepcao() {
        return numeroRecepcao;
    }

    public void setNumeroRecepcao(String numeroRecepcao) {
        this.numeroRecepcao = numeroRecepcao;
    }

    public String getInicioPiscina() {
        return inicioPiscina;
    }

    public void setInicioPiscina(String inicioPiscina) {
        this.inicioPiscina = inicioPiscina;
    }

    public String getFinalPiscina() {
        return finalPiscina;
    }

    public void setFinalPiscina(String finalPiscina) {
        this.finalPiscina = finalPiscina;
    }

    public String getInicioCafe() {
        return inicioCafe;
    }

    public void setInicioCafe(String inicioCafe) {
        this.inicioCafe = inicioCafe;
    }

    public String getFinalCafe() {
        return finalCafe;
    }

    public void setFinalCafe(String finalCafe) {
        this.finalCafe = finalCafe;
    }

    @Override
    public String toString() {
        return "Informacoes{" +
                "id=" + id +
                ", nomeWifi='" + nomeWifi + '\'' +
                ", senhaWifi='" + senhaWifi + '\'' +
                ", numeroRecepcao='" + numeroRecepcao + '\'' +
                ", inicioPiscina='" + inicioPiscina + '\'' +
                ", finalPiscina='" + finalPiscina + '\'' +
                ", inicioCafe='" + inicioCafe + '\'' +
                ", finalCafe='" + finalCafe + '\'' +
                '}';
    }
}
