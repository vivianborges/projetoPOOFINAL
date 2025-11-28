package com.delivery.main;

import com.delivery.view.TelaPrincipal;

public class Main {
    public static void main(String[] args) {


        javax.swing.SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}
