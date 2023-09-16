package edu.formularios;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;
import edu.pizza.especialidades.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPage {
    private JPanel jMainPanel;
    private JPanel MainPanel;
    private JLabel labelHome;
    private JComboBox comboBoxToppings;
    private JTextField textPizza;
    private JButton btnAddIngrediente;
    private JLabel lblTotal;
    private JList lista1;
    private JButton btnPreparePizza;
    private JComboBox comboBoxTipoPizza;
    private JRadioButton RadioButtonSizeS;
    private JRadioButton RadioButtonSizeM;
    private JRadioButton RadioButtonSizeB;
    private JButton btnRemoveIngrediente;
    private JList lista2;
    private JComboBox ComboRemoveItem;
    private DefaultListModel modelLista1 =  new DefaultListModel();
    private DefaultListModel<String> modeloLista2 = new DefaultListModel<>();
    private List<String> tiposPizza=new ArrayList<>();
    private Map<String, List<Topping>> pizzaToppings = new HashMap<>();
    private List<Topping> ingredientes = new ArrayList<>();
    private double total = 0;
    private double AdicionalPrice = 0;




    private void aplicarBorde(JComponent componente, Border borde) {
        componente.setBorder(borde);
    }

    public MainPage() {

        Border bordeNegroGrueso = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Aplicar el borde a todos los componentes principales del formulario
        aplicarBorde(jMainPanel, bordeNegroGrueso);
        //aplicarBorde(ComboPrepararPizza, bordeNegroGrueso);
        //aplicarBorde(txtPizza, bordeNegroGrueso);
        aplicarBorde(btnAddIngrediente, bordeNegroGrueso);
        //aplicarBorde(lblTotal, bordeNegroGrueso);
        // aplicarBorde(lista1, bordeNegroGrueso);
        aplicarBorde(btnPreparePizza, bordeNegroGrueso);
        //aplicarBorde(list2, bordeNegroGrueso);
        //aplicarBorde(comboTipoPizza, bordeNegroGrueso);
        aplicarBorde(btnRemoveIngrediente, bordeNegroGrueso);
        //aplicarBorde(ComboRemoverIngredientes, bordeNegroGrueso);
        aplicarBorde(RadioButtonSizeS, bordeNegroGrueso);
        aplicarBorde(RadioButtonSizeM, bordeNegroGrueso);
        aplicarBorde(RadioButtonSizeB, bordeNegroGrueso);

        MainPanel.setVisible(true);
        cargarToppings();
        loadTypePizza();


        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(RadioButtonSizeS);
        sizeGroup.add(RadioButtonSizeM);
        sizeGroup.add(RadioButtonSizeB);
        RadioButtonSizeS.setSelected(true); //Establece el tamaño por defecto

        RadioButtonSizeS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecio();
            }
        });

        RadioButtonSizeM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecio();
            }
        });

        RadioButtonSizeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecio();
            }
        });



        comboBoxTipoPizza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                modeloLista2.clear();

                // Obtén el tipo de pizza seleccionado por el usuario
                String tipoPizzaSeleccionada = (String) comboBoxTipoPizza.getSelectedItem();

                // Obtén la lista de toppings asociados a ese tipo de pizza desde el mapa pizzaToppings
                List<Topping> toppingsPizza = pizzaToppings.get(tipoPizzaSeleccionada);


                // Limpia la lista actual
                modelLista1.clear();


                // Agrega los toppings de la pizza seleccionada a la lista
                for (Topping topping : toppingsPizza) {
                    modelLista1.addElement(topping.toString());
                    lista1.setModel(modelLista1);
                }

                // Calcula el total de los precios de los toppings
                total = toppingsPizza.stream().mapToDouble(Topping::getPrecio).sum();

                lblTotal.setText(String.valueOf(total));

                LoadIngComboRemove();
                textPizza.setText(tipoPizzaSeleccionada);
            }
        });

        btnAddIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        Topping ingrediente = (Topping) comboBoxToppings.getSelectedItem();
                        modelLista1.addElement(ingrediente);
                        lista1.setModel(modelLista1);
                        total += ingrediente.getPrecio();
                        lblTotal.setText(String.valueOf(total));
                        DefaultComboBoxModel<String> ComboRemoveModel = (DefaultComboBoxModel<String>) ComboRemoveItem.getModel();
                        ComboRemoveModel.addElement(ingrediente.toString());

                        //Hace que el ingrediente seleccionado se muestre en el combo box de remover
                        Pizza pizza =new Pizza(textPizza.getName());
                        Topping topi;
                        for (int i=0; i < lista1.getModel().getSize(); i++){

                            topi=(Topping) lista1.getModel().getElementAt(i);
                            pizza.addTopping(topi);
                        }
            }
        });
        btnRemoveIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            Topping ingrediente = (Topping) comboBoxToppings.getSelectedItem();
//                        modelLista1.removeElement(ingrediente);

                     total -= ingrediente.getPrecio();
                        if (total < 0){
                            total = 0;
                        }
                        lista1.setModel(modelLista1);
                        lblTotal.setText(String.valueOf(total));
                        ComboRemoveItem.getSelectedItem();
//                        String ingredienteSeleccionado = (String) ComboRemoveItem.getSelectedItem();


//                Verificar si se ha seleccionado un ingrediente para remover
                if (ComboRemoveItem.getSelectedItem() != null) {
                    String ingredienteSeleccionado = (String) ComboRemoveItem.getSelectedItem();

                    // Obtener el precio del ingrediente seleccionado
                    String[] parts = ingredienteSeleccionado.split("Q");
                    double precioIngrediente = Double.parseDouble(parts[1].trim());


                    // Quitar el ingrediente de lista1
                    modelLista1.removeElement(ingrediente);
                    lista1.setModel(modelLista1);

                    // Restar el precio del ingrediente a total
                    total -= precioIngrediente;
                    lblTotal.setText(String.valueOf(total));

                    modeloLista2.removeElement(ingrediente);
                    lista1.setModel(modeloLista2);


                    DefaultComboBoxModel<String> comboRemoverModel = (DefaultComboBoxModel<String>) ComboRemoveItem.getModel();
                    comboRemoverModel.removeElement(ingredienteSeleccionado);
                } else
                    if (ComboRemoveItem.getSelectedItem() == null) {
                        AdicionalPrice += 0;
                    }

            }
        });
        btnPreparePizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pizza pizza = new Pizza(btnPreparePizza.getText());
                Topping topi;

                for (int i = 0; i < lista1.getModel().getSize(); i++) {
                    topi = (Topping) lista1.getModel().getElementAt(i);
                    pizza.addTopping(topi);
                }

                if (lista1.getModel().getSize() <= 0) {
                    JOptionPane.showMessageDialog(null, "No hay ingredientes");
                } else if (btnPreparePizza.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay nombre de pizza");
                } else {
                    pizza.prepare();

                }
            }
        });
        lista1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(lista1.getSelectedIndex() != -1){
                    int index = lista1.getSelectedIndex();
                    modelLista1.remove(index);
                    total -= ingredientes.get(index).getPrecio();
                    lblTotal.setText(String.valueOf(total));
                }
            }
        });
    }


    public JPanel getjMainPanel() {
        return jMainPanel;
    }

    private void LoadIngComboRemove() {
        ComboRemoveItem.removeAllItems(); // Limpia los elementos existentes en ComboRemoverIngredientes
        int itemCount = modelLista1.getSize(); // Obtiene la cantidad de elementos en modeloLista

        // Agrega cada elemento de modeloLista a ComboRemoverIngredientes
        for (int i = 0; i < itemCount; i++) {
            String ingrediente = (String) modelLista1.getElementAt(i);
            ComboRemoveItem.addItem(ingrediente);
        }
    }

    private void actualizarPrecio() {
        double precioTotal = total;

        if (RadioButtonSizeS.isSelected()) {
            precioTotal += total * 0.00;
        } else
        if (RadioButtonSizeM.isSelected()) {
            precioTotal += total * 0.10; // 10% de recargo para tamaño mediano
        } else if (RadioButtonSizeB.isSelected()) {
            precioTotal += total * 0.15; // 15% de recargo para tamaño grande
        }

        lblTotal.setText("Q" + precioTotal); // Actualizando el texto de lblTotal
    }





    private void loadTypePizza() {
        PizzaCalamaresRomana pizzaCalamaresRomana = new PizzaCalamaresRomana("Pizza de Calamares Romana");
        tiposPizza.add(pizzaCalamaresRomana.getName());

        PizzaDesayunoSalvaje pizzaDesayunoSalvaje = new PizzaDesayunoSalvaje("Pizza Desayuno Salvaje");
        tiposPizza.add(pizzaDesayunoSalvaje.getName());

        PizzaFrutasTropicales pizzaFrutasTropicales = new PizzaFrutasTropicales("Pizza de Frutas Tropicales");
        tiposPizza.add(pizzaFrutasTropicales.getName());

        PizzaPollo_a_la_Parrilla_y_MielPicante pizzaPollo_a_la_Parrilla_y_MielPicante = new PizzaPollo_a_la_Parrilla_y_MielPicante("Pizza de Pollo a la Parrilla y Miel Picante");
        tiposPizza.add(pizzaPollo_a_la_Parrilla_y_MielPicante.getName());

        PizzaSalmonAhumadoyCaviar pizzaSalmonAhumadoyCaviar = new PizzaSalmonAhumadoyCaviar("Pizza de Salmon Ahumado y Caviar");
        tiposPizza.add(pizzaSalmonAhumadoyCaviar.getName());


        Pizza custompizza = new Pizza("Preparala Ya! (Pizza personalizada)");
        tiposPizza.add(custompizza.getName());

        DefaultComboBoxModel model = new DefaultComboBoxModel(tiposPizza.toArray());
        comboBoxTipoPizza.setModel(model);

        pizzaToppings.put(pizzaCalamaresRomana.getName(), pizzaCalamaresRomana.getToppings());
        pizzaToppings.put(pizzaDesayunoSalvaje.getName(), pizzaDesayunoSalvaje.getToppings());
        pizzaToppings.put(pizzaFrutasTropicales.getName(), pizzaFrutasTropicales.getToppings());
        pizzaToppings.put(pizzaPollo_a_la_Parrilla_y_MielPicante.getName(), pizzaPollo_a_la_Parrilla_y_MielPicante.getToppings());
        pizzaToppings.put(pizzaSalmonAhumadoyCaviar.getName(), pizzaSalmonAhumadoyCaviar.getToppings());
        pizzaToppings.put(custompizza.getName(), custompizza.getToppings());

    }


    private void cargarToppings() {
        ingredientes.add(new Topping("Champiñones", 10));
        ingredientes.add(new Topping("Tomate", 5));
        ingredientes.add(new Topping("Cebolla", 8));
        ingredientes.add(new Topping("Salsa de tomate", 10));
        ingredientes.add(new Topping("Queso cheddar", 11));
        ingredientes.add(new Topping("Queso mozzarella", 12));
        ingredientes.add(new Topping("Queso parmesano", 13));
        ingredientes.add(new Topping("Huevos reuvueltos", 5));
        ingredientes.add(new Topping("Tocino crujiente",8));
        ingredientes.add(new Topping("Salchichas picantes",5));
        ingredientes.add(new Topping("Champiñones salteados",7));
        ingredientes.add(new Topping("Queso cheddar rallado",10));
        ingredientes.add(new Topping("Cebolla verde picada",6));
        ingredientes.add(new Topping("Salsa de sriracha",9));

        DefaultComboBoxModel model = new DefaultComboBoxModel(ingredientes.toArray());
        comboBoxToppings.setModel(model);
    }



    private void prepararPizza() {
        String tipoPizzaSeleccionada = (String) comboBoxTipoPizza.getSelectedItem();
        if (tipoPizzaSeleccionada == null) {
            mostrarError("Selecciona un tipo de pizza antes de prepararla.");
            return;
        }

        if (modelLista1.isEmpty()) {
            mostrarError("Agrega al menos un ingrediente a la pizza antes de prepararla.");
            return;
        }

        String nombrePizza = textPizza.getText().trim();
        if (nombrePizza.isEmpty()) {
            mostrarError("Ingresa un nombre para la pizza antes de prepararla.");
            return;
        }

        Pizza pizza = new Pizza(tipoPizzaSeleccionada);
        modeloLista2.clear();

        ingredientes = pizzaToppings.get(tipoPizzaSeleccionada);

        modeloLista2.addElement("Preparando.... " + tipoPizzaSeleccionada);
        modeloLista2.addElement("Agregando toppings");

        for (int i = 0; i < lista1.getModel().getSize(); i++) {
            String ingrediente = (String) lista1.getModel().getElementAt(i);
            String nombreIngrediente = ingrediente.split("Q")[0].trim();
            modeloLista2.addElement("- " + nombreIngrediente);
        }

        modeloLista2.addElement("Pizza lista!");

        lista2.setModel(modeloLista2);
    }

//    System.out.println("Preparando..... " + name);
//        System.out.println("Adding toppings:");
//        for (Topping topping : toppings) {
//        System.out.println("- " + topping.getNombre() + " Q" + topping.getPrecio());
//        //put 1 second delay
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//        System.out.println("La pizza esta lista!");

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);

    }
}





