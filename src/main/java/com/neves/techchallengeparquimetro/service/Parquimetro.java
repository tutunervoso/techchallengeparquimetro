package com.neves.techchallengeparquimetro.service;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.neves.techchallengeparquimetro.DAO.ParquimetroDAO;
import com.neves.techchallengeparquimetro.controller.MongoDBConnection;
import com.neves.techchallengeparquimetro.model.Carro;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Parquimetro {
    private ParquimetroDAO parquimetroDAO;

    public Parquimetro() {
        MongoDatabase database = MongoDBConnection.createConnection();
        this.parquimetroDAO = new ParquimetroDAO(database);
    }

    public Carro estacionarCarro(String placa) {
        LocalDate now = LocalDate.now();
        Date agora = java.sql.Date.valueOf(now);
        Carro carro = new Carro(null, placa, agora, null);
        parquimetroDAO.adicionarCarro(carro);
        return carro;
    }

    public Optional<Carro> sairCarro(String carroId) {
        Carro carro = parquimetroDAO.pegarPorId(carroId);
        if (carro == null) {
            return Optional.empty();
        }
        LocalDate now = LocalDate.now();
        Date agora = java.sql.Date.valueOf(now);
        carro.setSaida(agora);
        parquimetroDAO.atualizarCarro(carro);
        return Optional.of(carro);
    }

    public List<Carro> mostrarCarros() {
        return parquimetroDAO.listarCarros();
    }

    public Optional<Carro> mostrarCarroPorId(String carroId) {
        return Optional.ofNullable(parquimetroDAO.pegarPorId(carroId));
    }

    public boolean apagarCarro(String carroId) {
        DeleteResult result = parquimetroDAO.apagarCarro(carroId);
        return result.wasAcknowledged() && result.getDeletedCount() > 0;
    }
}