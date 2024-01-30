package com.neves.techchallengeparquimetro.DAO;

import com.mongodb.client.MongoDatabase;
import com.neves.techchallengeparquimetro.model.Carro;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class ParquimetroDAO {
    private MongoCollection<Document> parquimetroCollection;

    public ParquimetroDAO(MongoDatabase database) {
        this.parquimetroCollection = database.getCollection("parquimetro");
    }

    public Carro adicionarCarro(Carro carro) {
        Document document = new Document()
                .append("placa", carro.getPlaca())
                .append("entrada", carro.getEntrada());

        parquimetroCollection.insertOne(document);
        carro.setId(document.getObjectId("_id").toString());
        return carro;
    }

    public UpdateResult atualizarCarro(Carro carro) {
        return parquimetroCollection.replaceOne(Filters.eq("_id", new ObjectId(carro.getId())),
                new Document("placa", carro.getPlaca())
                        .append("entrada", carro.getEntrada())
                        .append("saida", carro.getSaida()));
    }

    public DeleteResult apagarCarro(String carroId) {
        return parquimetroCollection.deleteOne(Filters.eq("_id", new ObjectId(carroId)));
    }

    public List<Carro> listarCarros() {
        List<Carro> carros = new ArrayList<>();
        for (Document document : parquimetroCollection.find()) {
            carros.add(new Carro(
                    document.getObjectId("_id").toString(),
                    document.getString("placa"),
                    document.getDate("entrada"),
                    document.getDate("saida")
            ));
        }
        return carros;
    }

    public Carro pegarPorId(String carroId) {
        Document document = parquimetroCollection.find(Filters.eq("_id", new ObjectId(carroId))).first();
        if (document == null) {
            return null;
        }
        return new Carro(
                document.getObjectId("_id").toString(),
                document.getString("placa"),
                document.getDate("entrada"),
                document.getDate("saida")
        );
    }
}
