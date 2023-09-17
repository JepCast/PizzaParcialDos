package edu.pizza;


import edu.formularios.MainPage;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Formulario Pizza");
        frame.setTitle("Pizza");
        frame.setContentPane(new MainPage().getjMainPanel());
        frame.pack();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
