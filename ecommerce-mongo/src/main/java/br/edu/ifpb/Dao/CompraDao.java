package br.edu.ifpb.Dao;

import br.edu.ifpb.model.Compra;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CompraDao {
    private MongoCollection<Compra> collection;

    public CompraDao(MongoDatabase db){
        this.collection=db.getCollection("compras", Compra.class);


    }

    public void salvarCompra(Compra com){
        this.collection.insertOne(com);
    }
}
