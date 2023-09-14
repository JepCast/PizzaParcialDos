package edu.formularios;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class frmPizza {
    private JPanel jpanelPrincipal;
    private JComboBox comboBoxToppings;
    private JTextField textPizza;
    private JButton btnAddIngrediente;
    private JLabel lblTotal;
    private JList lista1;
    private JButton txtPizza;
    private DefaultListModel modelLista =  new DefaultListModel();
    private List<Topping> ingredientes = new ArrayList<>();
    private double total = 0;


    public frmPizza(){
        cargarToppings();
        btnAddIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Topping ingrediente = (Topping) comboBoxToppings.getSelectedItem();
                modelLista.addElement(ingrediente);
                lista1.setModel(modelLista);
                total += ingrediente.getPrecio();
                lblTotal.setText(String.valueOf(total));
            }
        });
        txtPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pizza pizza = new Pizza(txtPizza.getText());
                Topping topi;

                for (int i = 0; i < lista1.getModel().getSize(); i++){
                    topi = (Topping) lista1.getModel().getElementAt(i);
                    pizza.addTopping(topi);
                }

                if(lista1.getModel().getSize() <= 0) {
                    JOptionPane.showMessageDialog(null, "No hay ingredientes");
                }else if (txtPizza.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay nombre de pizza");
                }else {
                    pizza.prepare();
                }
            }
        });
    }

    private void cargarToppings(){
        ingredientes.add(new Topping("Champiñones", 5.1));
        ingredientes.add(new Topping("Tomate", 3.6));
        ingredientes.add(new Topping("Cebolla", 6.5));
        ingredientes.add(new Topping("Salchicha", 10.5));
        ingredientes.add(new Topping("Calamares", 11.55));
        ingredientes.add(new Topping("Chucho", 14.55));

        DefaultComboBoxModel model = new DefaultComboBoxModel(ingredientes.toArray());
        comboBoxToppings.setModel(model);
    }

    public JPanel getJpanelPrincipal() {
        return jpanelPrincipal;
    }
}



