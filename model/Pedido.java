package com.delivery.model;

public class Pedido implements Calculavel {

    private Cliente cliente;
    private String produto;
    private double valor;
    private boolean entregue;

    public Pedido(Cliente cliente, String produto, double valor) {
        this.cliente = cliente;
        this.produto = produto;
        this.valor = valor;
        this.entregue = false;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getProduto() {
        return produto;
    }

    public double getValor() {
        return valor;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    @Override
    public double calcularTotal() {
        return valor;
    }

    @Override
    public String toString() {
        return cliente.getNome() + " - " + produto + " (R$ " + valor + ")";
    }
}
