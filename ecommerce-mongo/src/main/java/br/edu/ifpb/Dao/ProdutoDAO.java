package br.edu.ifpb.Dao;

import br.edu.ifpb.model.Produto;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ProdutoDAO {
    protected final MongoCollection<Produto> collection;

    public ProdutoDAO(MongoDatabase db){
        this.collection=db.getCollection("produtos", Produto.class);
    }
    public  void salvarProduto(Produto pro){
        this.collection.insertOne(pro);

    }

    public Produto buscarPorId(String id){
        return  this.collection.find(eq("_id", id)).first();
    }


    public List<Produto> buscarTodos(){
        return  this.collection.find().into(new ArrayList<>());
    }




}
