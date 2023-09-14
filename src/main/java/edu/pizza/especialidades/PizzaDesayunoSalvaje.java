package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaDesayunoSalvaje extends Pizza {

    public PizzaDesayunoSalvaje(String name, Topping... toppings){
        super(name, toppings);
        IngredientesPizzaDesayunoSalvaje();

    }

    private void IngredientesPizzaDesayunoSalvaje() {
        addTopping(new Topping("Huevos reuvueltos", 5));
        addTopping(new Topping("Tocino crujiente",8));
        addTopping(new Topping("Salchichas picantes",5));
        addTopping(new Topping("Champiñones salteados",7));
        addTopping(new Topping("Queso cheddar rallado",10));
        addTopping(new Topping("Cebolla verde picada",6));
        addTopping(new Topping("Salsa de sriracha",9));
    }
}
