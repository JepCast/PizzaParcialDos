package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaPollo_a_la_Parrilla_y_MielPicante extends Pizza {

    public PizzaPollo_a_la_Parrilla_y_MielPicante(String name, Topping... toppings){
        super(name, toppings);
        IngredientesPizzaPollo_a_la_Parrilla_y_MielPicante();

    }

    private void IngredientesPizzaPollo_a_la_Parrilla_y_MielPicante() {
        addTopping(new Topping("Cilantro fresco", 5));
        addTopping(new Topping("Miel picante",8));
        addTopping(new Topping("Maíz dulce asado",5));
        addTopping(new Topping("Cebolla morada en rodajas finas",7));
        addTopping(new Topping("Pechuga de pollo a la parrilla en tiras",10));
        addTopping(new Topping("Pimientos jalapeños en rodajas",6));
        addTopping(new Topping("Queso mozzarella",9));
    }
}
