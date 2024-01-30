package com.neves.techchallengeparquimetro.model;

import java.util.Date;

public class Carro {
    private String id;
    private String placa;
    private Date entrada;
    private Date saida;

    public Carro(String id, String placa, Date entrada, Date saida) {
        this.id = id;
        this.placa = placa;
        this.entrada = entrada;
        this.saida = saida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id='" + id + '\'' +
                ", placa='" + placa + '\'' +
                ", entrada=" + entrada +
                ", saida=" + saida +
                '}';
    }
}
