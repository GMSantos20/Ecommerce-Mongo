package br.edu.ifpb.model;

import java.math.BigDecimal;

public class PagamentoCartao extends Pagamento{
    private String  nomeImpresso;
    private String numero;


    public PagamentoCartao(BigDecimal valor, String nomeImpresso, String numero){
        super("Cartao",valor);
        this.nomeImpresso=nomeImpresso;
        this.numero=numero;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }



}
