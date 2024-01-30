package com.neves.techchallengeparquimetro;

import com.neves.techchallengeparquimetro.model.Carro;
import com.neves.techchallengeparquimetro.service.Parquimetro;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Parquimetro parquimetro = new Parquimetro();

        // Estacione um veículo
        Carro carroEstacionado = parquimetro.estacionarCarro("SSS-8888");
        System.out.println("Veículo estacionado: " + carroEstacionado);

        // Liste um veículo específico
        Optional<Carro> carroOptional = parquimetro.mostrarCarroPorId(carroEstacionado.getId());
        System.out.println("Veículo estacionado: " + carroOptional);

        // Saia de um veículo
        Optional<Carro> carroSaindo = parquimetro.sairCarro(carroEstacionado.getId());
        if (carroSaindo.isPresent()) {
            System.out.println("Veículo saindo: " + carroSaindo.get());
        } else {
            System.out.println("Veículo não encontrado");
        }

        // Exclua um veículo
        boolean isDeleted = parquimetro.apagarCarro(carroEstacionado.getId());
        System.out.println("Veículo excluído: " + isDeleted);

        // Liste todos os veículos estacionados
        List<Carro> carroList = parquimetro.mostrarCarros();
        System.out.println("Veículos estacionados: " + carroList);

    }
}