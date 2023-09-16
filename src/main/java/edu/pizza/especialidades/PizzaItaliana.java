package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaItaliana extends Pizza {
   private String salsa;

    public  PizzaItaliana(String name,  String salsa, Topping... toppings){
        super(name,

                toppings);
        this.salsa=salsa;

    }



    public String getSalsa() {
        return salsa;
    }

    public void setSalsa(String salsa) {
        this.salsa = salsa;
    }

}
