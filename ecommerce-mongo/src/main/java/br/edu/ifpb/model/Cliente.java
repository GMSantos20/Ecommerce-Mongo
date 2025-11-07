package br.edu.ifpb.model;

import org.bson.types.ObjectId;

public class Cliente {

    private ObjectId id;
    private String nome;
    private String cpf;

    public Cliente(){

    }
    public Cliente(String nome, String cpf){
        this.nome=nome;
        this.cpf=cpf;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }



}
