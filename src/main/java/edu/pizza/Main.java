package edu.pizza;


import edu.formularios.frmPizza;
import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;
import edu.pizza.especialidades.PizzaItaliana;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("frmPizza");
        frame.setTitle("Pizza");
        frame.setContentPane(new frmPizza().getJpanelPrincipal());
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        //ejercicio 1
//        Pizza pizza = new Pizza("Pizza Margherita");
//        pizza.addTopping(new Topping("Champiñones", 10.99));
//        pizza.addTopping(new Topping("Mozzarella", 5));
//        pizza.addTopping(new Topping("Cebolla", 6));
//        pizza.addTopping(new Topping("Tomate", 7));
//        pizza.prepare();

//        quiten la mozarella
//        vuelvan a preparar
//        pizza.removeTopping(1);
//        pizza.prepare();










//        Ejercicio 2 heredar
//        Creamos una instancia de la clase PizzaItaliana y agregamos varios toppings
//        a ella utilizando
//        el método "agregar" de la clase Topping.
//        PizzaItaliana pizzaItaliana =
//                new PizzaItaliana("Pizza Italiana", 10.99, "Salsa de tomate", new Topping("Tomato"), new Topping("Mozzarella"), new Topping("Basil"));
//        pizzaItaliana.prepare();


//        Ejercicio 3
//        Topping pepperoni = new Topping("Pepperoni");
//        pepperoni.agregarIngrediente("queso");
//        pepperoni.agregarIngrediente("champiñones");
//        System.out.println(pepperoni); // Salida: Topping{nombre='Pepperoni', ingredientes=['queso']}


    }
}
