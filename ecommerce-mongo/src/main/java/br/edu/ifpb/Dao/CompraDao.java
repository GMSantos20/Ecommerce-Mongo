package br.edu.ifpb.Dao;

import br.edu.ifpb.model.Compra;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CompraDao {
    private MongoCollection<Compra> collection;

    public CompraDao(MongoDatabase db){
        this.collection=db.getCollection("compras", Compra.class);


    }

    public void salvarCompra(Compra com){
        this.collection.insertOne(com);
    }

    public List<Compra> buscarTodosPeloIdCliente(String idCliente){
       return this.collection.find(eq("cliente.$id", new ObjectId(idCliente))).into(new ArrayList<>());


    }
}
