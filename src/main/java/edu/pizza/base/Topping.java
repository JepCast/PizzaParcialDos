package edu.pizza.base;

import java.util.ArrayList;

public class Topping {
    private String nombre; // Nombre del topping
    private double precio;
    private ArrayList<String> ingredientes = new ArrayList<>(); // Ingredientes del topping

    public void agregarIngrediente(String ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    public double getPrecio() {
        return precio;
    }

    public Topping(String nombre, double precio) {

        this.nombre = nombre;
        this.precio=precio;
    }

    @Override
    public String toString() {
        return  nombre +"Q" + precio ;
    }


    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }


}
