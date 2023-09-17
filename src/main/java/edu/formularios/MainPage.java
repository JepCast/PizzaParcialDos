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
    private JList lista2;
    private DefaultListModel modelLista1 =  new DefaultListModel();
    private DefaultListModel<String> modeloLista2 = new DefaultListModel<>();
    private List<String> tiposPizza=new ArrayList<>();
    private Map<String, List<Topping>> pizzaToppings = new HashMap<>();
    private List<Topping> ingredientes = new ArrayList<>();

    private double total = 0;

    private void aplicarBorde(JComponent componente, Border borde) {componente.setBorder(borde);}

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
        aplicarBorde(RadioButtonSizeS, bordeNegroGrueso);
        aplicarBorde(RadioButtonSizeM, bordeNegroGrueso);
        aplicarBorde(RadioButtonSizeB, bordeNegroGrueso);

        MainPanel.setVisible(true);
        cargarToppings();
        loadTypePizza();

        //Creacion del grupo para el tamaño de la pizza
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(RadioButtonSizeS);
        sizeGroup.add(RadioButtonSizeM);
        sizeGroup.add(RadioButtonSizeB);
        RadioButtonSizeS.setSelected(true); //Establece el tamaño por defecto


        comboBoxTipoPizza.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {

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
                textPizza.setText(tipoPizzaSeleccionada);
            }
        });

        btnAddIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloLista2.clear();
                // Obtén el topping seleccionado por el usuario
                Topping ingrediente= (Topping) comboBoxToppings.getSelectedItem();

                // Agrega el topping a la lista
                modelLista1.addElement(ingrediente);
                lista1.setModel(modelLista1);
                // Agrega el precio del topping al total
                total += ingrediente.getPrecio();
                lblTotal.setText(String.valueOf(total));


                Pizza pizza = new Pizza(textPizza.getName());
                Topping topi;

                // Agrega los toppings de la pizza seleccionada a la lista
                for (int i = 0; i < lista1.getModel().getSize(); i++) {

                    topi = (Topping) lista1.getModel().getElementAt(i);
                    pizza.addTopping(topi);
                }
            }
        });


        btnPreparePizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepararPizza();
            }
        });


        // Agrega un listener para el evento de doble clic en la lista de toppings para la eliminación de los ingredientes
        lista1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Obténcion de el tipo de pizza seleccionado por el usuario
                String tipoPizzaSeleccionada = (String) comboBoxTipoPizza.getSelectedItem();
                // Obténcion de  la lista de toppings asociados a ese tipo de pizza desde el mapa pizzaToppings
                List<Topping> toppingsPizza = pizzaToppings.get(tipoPizzaSeleccionada);

                // Obténcion de el topping seleccionado por el usuario
                if (e.getClickCount() == 2) {

                    if (lista1.getSelectedIndex()!= -1) {

                        String toppingSeleccionado = (String) modelLista1.getElementAt(lista1.getSelectedIndex());

                        // Encuentra el topping correspondiente en la lista de toppingsPizza
                        Topping topping = null;
                        for (Topping t : toppingsPizza) {
                            if (t.toString().equals(toppingSeleccionado)) {
                                topping = t;
                                break;
                            }
                        }


                        // Encuentra el topping correspondiente en la lista de ComboPrepararPizza

                        for (int i = 0; i < comboBoxToppings.getItemCount(); i++) {
                            Topping temp = (Topping) comboBoxToppings.getItemAt(i);
                            if (temp.toString().equals(toppingSeleccionado)) {
                                topping = temp;
                                break;
                            }
                        }

                        if (topping != null) {
                            // Resta el precio del topping al total
                            total -= topping.getPrecio();
                            lblTotal.setText(String.valueOf(total));

                            // Elimina el topping de la lista
                            modelLista1.removeElementAt(lista1.getSelectedIndex());
                        }
                    }
                }
            }
        });

        //Aca se establece el tamaño de la pizza
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





//        lista1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) { // Verificar si fue un doble clic
//                    int selectedIndex = lista1.getSelectedIndex();
//                    if (selectedIndex != -1) {
//                        modelLista1.remove(selectedIndex);
//                        total -= ((Topping) modelLista1.getselectedIndex().getElementAt(selectedIndex)).getPrecio();
//                        actualizarPrecio();
//                    }
//                }
//            }
//        });
//
//        lista1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) { // Verificar si fue un doble clic
//                    int selectedIndex = lista1.getSelectedIndex();
//                    if (selectedIndex != -1) {
//                        lista1.getSelectedIndex();
//                        double precioIngrediente = ((Topping) modelLista1.getElementAt(selectedIndex)).getPrecio();
//                        total -= precioIngrediente;
////                      total -= ((Topping) modelLista1.getElementAt(selectedIndex)).getPrecio();
////                      Topping ingredienteSeleccionado = (Topping) modelLista1.getElementAt(selectedIndex);
//                        modelLista1.remove(selectedIndex);
//
////                        Topping ingredienteSeleccionado = (Topping) modelLista1.getElementAt(selectedIndex);
//////                        total -= ingredienteSeleccionado.getPrecio();
////                        modelLista1.remove(selectedIndex);
//                        actualizarPrecio();
//                    }
//                }
//            }
//        });


    }

    public JPanel getjMainPanel() {
        return jMainPanel;
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

        modeloLista2.addElement(" \nPreparando.... " + pizza);
        modeloLista2.addElement(tipoPizzaSeleccionada);
        modeloLista2.addElement(" \nAgregando toppings ");

        for (int i = 0; i < lista1.getModel().getSize(); i++) {
            String ingrediente = (String) lista1.getModel().getElementAt(i);
            String nombreIngrediente = ingrediente.split("\nQ")[0].trim();
            modeloLista2.addElement("\n- " + nombreIngrediente);
        }

        modeloLista2.addElement("\nTu pizza esta lista!");
    }


    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);

    }
}