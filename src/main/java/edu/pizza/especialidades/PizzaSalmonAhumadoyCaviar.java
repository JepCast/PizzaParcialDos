package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaSalmonAhumadoyCaviar extends Pizza {

    public PizzaSalmonAhumadoyCaviar(String name, Topping... toppings){
        super(name, toppings);
        IngredientesPizzaSalmonAhumadoyCaviar();

    }

    private void IngredientesPizzaSalmonAhumadoyCaviar() {
        addTopping(new Topping("Crema agria", 5));
        addTopping(new Topping("Queso mascarpone",8));
        addTopping(new Topping("Cebolla roja en rodajas finas",5));
        addTopping(new Topping("Caviar de trucha",20));
        addTopping(new Topping("Salmón ahumado en lonchas",10));
        addTopping(new Topping("Eneldo fresco",6));
        addTopping(new Topping("Alcaparras enjuagadas",9));
    }
}
