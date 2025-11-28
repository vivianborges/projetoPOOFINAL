package com.delivery.controller;

import com.delivery.model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    private List<Pedido> pedidos = new ArrayList<>();


    public void adicionar(Pedido pedido) {
        pedidos.add(pedido);
    }


    public void remover(int index) {
        if (index >= 0 && index < pedidos.size()) {
            pedidos.remove(index);
        }
    }


    public void marcarComoEntregue(int index) {
        if (index >= 0 && index < pedidos.size()) {
            pedidos.get(index).setEntregue(true);
        }
    }


    public List<Pedido> listar() {
        return pedidos;
    }


    public int getTotalPedidos() {
        return pedidos.size();
    }


    public double calcularTotalVendas() {
        double total = 0;
        for (Pedido p : pedidos) {
            total += p.calcularTotal();
        }
        return total;
    }
}
