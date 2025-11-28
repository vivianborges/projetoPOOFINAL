package com.delivery.model;

public class Cliente extends Pessoa {

    public Cliente(String nome) {
        super(nome);
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
