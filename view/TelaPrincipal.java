package com.delivery.view;

import com.delivery.controller.PedidoController;
import com.delivery.model.Cliente;
import com.delivery.model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private PedidoController controller = new PedidoController();


    private JTextField txtCliente;
    private JTextField txtProduto;
    private JTextField txtValor;
    private JButton btnCadastrar;


    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JButton btnExcluir;
    private JButton btnEntregar;


    private JLabel lblTotalPedidos;
    private JLabel lblTotalVendas;

    public TelaPrincipal() {
        setTitle("Sistema de Delivery");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Cadastrar Pedido", criarPainelCadastro());
        abas.addTab("Lista de Pedidos", criarPainelListagem());

        add(abas, BorderLayout.CENTER);
        add(criarPainelTotais(), BorderLayout.SOUTH);
    }


    private JPanel criarPainelCadastro() {
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 2, 10, 10));

        painel.add(new JLabel("Nome do Cliente:"));
        txtCliente = new JTextField();
        painel.add(txtCliente);

        painel.add(new JLabel("Produto:"));
        txtProduto = new JTextField();
        painel.add(txtProduto);

        painel.add(new JLabel("Valor (R$):"));
        txtValor = new JTextField();
        painel.add(txtValor);

        btnCadastrar = new JButton("Cadastrar");
        painel.add(btnCadastrar);

        btnCadastrar.addActionListener(e -> cadastrarPedido());

        return painel;
    }


    private JPanel criarPainelListagem() {
        JPanel painel = new JPanel(new BorderLayout());

        modeloTabela = new DefaultTableModel(
                new Object[]{"Cliente", "Produto", "Valor", "Status"}, 0
        );

        tabela = new JTable(modeloTabela);

        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.CENTER);

        JPanel botoes = new JPanel();

        btnExcluir = new JButton("Excluir");
        btnEntregar = new JButton("Marcar como Entregue");

        botoes.add(btnEntregar);
        botoes.add(btnExcluir);

        painel.add(botoes, BorderLayout.SOUTH);

        btnExcluir.addActionListener(e -> excluirPedido());
        btnEntregar.addActionListener(e -> entregarPedido());

        return painel;
    }


    private JPanel criarPainelTotais() {
        JPanel painel = new JPanel();

        lblTotalPedidos = new JLabel("Total de pedidos: 0");
        lblTotalVendas = new JLabel("Total em vendas: R$ 0.00");

        painel.add(lblTotalPedidos);
        painel.add(new JLabel(" | "));
        painel.add(lblTotalVendas);

        return painel;
    }


    private void cadastrarPedido() {
        try {
            String nome = txtCliente.getText();
            String produto = txtProduto.getText();
            double valor = Double.parseDouble(txtValor.getText());

            Cliente cliente = new Cliente(nome);
            Pedido pedido = new Pedido(cliente, produto, valor);

            controller.adicionar(pedido);

            atualizarTabela();
            atualizarTotais();

            JOptionPane.showMessageDialog(this, "Pedido cadastrado!");

            txtCliente.setText("");
            txtProduto.setText("");
            txtValor.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
        }
    }


    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

        for (Pedido p : controller.listar()) {
            modeloTabela.addRow(new Object[]{
                    p.getCliente().getNome(),
                    p.getProduto(),
                    p.getValor(),
                    p.isEntregue() ? "Entregue" : "Pendente"
            });
        }
    }


    private void atualizarTotais() {
        lblTotalPedidos.setText("Total de pedidos: " + controller.getTotalPedidos());
        lblTotalVendas.setText("Total em vendas: R$ " + controller.calcularTotalVendas());
    }


    private void excluirPedido() {
        int index = tabela.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para excluir.");
            return;
        }

        controller.remover(index);

        atualizarTabela();
        atualizarTotais();
    }


    private void entregarPedido() {
        int index = tabela.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para marcar como entregue.");
            return;
        }

        controller.marcarComoEntregue(index);

        atualizarTabela();
        atualizarTotais();
    }
}
