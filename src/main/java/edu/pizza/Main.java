package edu.pizza;


import edu.formularios.MainPage;
import edu.formularios.frmPizza;
import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;
import edu.pizza.especialidades.PizzaItaliana;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Formulario Pizza");
        frame.setTitle("Pizza");
        frame.setContentPane(new MainPage().getjMainPanel());
        frame.pack();
        frame.setSize(425, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
