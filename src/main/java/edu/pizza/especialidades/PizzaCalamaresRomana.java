package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaCalamaresRomana extends Pizza {

    public PizzaCalamaresRomana(String name, Topping... toppings){
        super(name, toppings);
        IngredientesPizzaCalamaresRomana();

    }

    public void IngredientesPizzaCalamaresRomana() {
        addTopping(new Topping("Anillos de calamar fritos", 5));
        addTopping(new Topping("Salsa marinara",8));
        addTopping(new Topping("Queso parmesano rallado",5));
        addTopping(new Topping("Aceitunas negras en rodajas",7));
        addTopping(new Topping("Albahaca fresca",10));
        addTopping(new Topping("Limón en rodajas finas",6));
        addTopping(new Topping("Ajo picado",9));
    }
}
