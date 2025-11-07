package br.edu.ifpb.model;

import java.math.BigDecimal;

public class Pagamento {

    private String tipo;
    private BigDecimal valor;

    public Pagamento(){}

    public Pagamento(String tipo, BigDecimal valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }


}
