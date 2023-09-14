package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaFrutasTropicales extends Pizza {

    public PizzaFrutasTropicales(String name, Topping... toppings){
        super(name, toppings);
        IngredientesPizzaFrutasTropicales();

    }

    private void IngredientesPizzaFrutasTropicales() {
        addTopping(new Topping("Piña en rodajas", 5));
        addTopping(new Topping("Mango en cubos",8));
        addTopping(new Topping("Kiwi en rodajas finas",5));
        addTopping(new Topping("Queso ricotta",7));
        addTopping(new Topping("Salsa de mango agridulce",10));
        addTopping(new Topping("Menta fresca",6));
        addTopping(new Topping("Coco rallado tostado",9));
    }
}
