package br.edu.ifpb.Dao;

import br.edu.ifpb.model.Cliente;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ClienteDao {

    protected final MongoCollection<Cliente> collection;

    public ClienteDao(MongoDatabase db){
        this.collection= db.getCollection("clientes", Cliente.class);
    }

    public void salvar(Cliente cli){
        this.collection.insertOne(cli);

    }

    public Cliente buscarPorId(String id){
        return  this.collection.find(eq("_id", id)).first();
    }
    public Cliente buscarPorCPF(String cpf){
        return  this.collection.find(eq("cpf", cpf)).first();
    }

    public List<Cliente> buscarTodos(){
        return  this.collection.find().into(new ArrayList<>());
    }
}
