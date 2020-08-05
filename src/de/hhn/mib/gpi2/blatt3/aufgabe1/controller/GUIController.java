package de.hhn.mib.gpi2.blatt3.aufgabe1.controller;

import de.hhn.mib.gpi2.blatt3.aufgabe1.model.Order;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.Pizza;
import de.hhn.mib.gpi2.blatt3.aufgabe1.view.Button;
import de.hhn.mib.gpi2.blatt3.aufgabe1.view.MyMenuBar;
import de.hhn.mib.gpi2.blatt3.aufgabe1.view.PizzaConfigPanel;
import de.hhn.mib.gpi2.blatt3.aufgabe1.view.Window;
import de.hhn.mib.gpi2.blatt4.aufgabe1.io.DataStorage;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Alin Gavrila
 * @version 1.0
 */
public class GUIController {
    private static Window mainView;
    private PizzaConfigPanel pizzaConfigPanel;
    private Order order;
    private Pizza pizza;
    private DataStorage dataStorage = new DataStorage();

    /**
     * GUIController-Instanz.
     */
    public GUIController() {
        Window window = new Window();
        pizzaConfigPanel = window.getPizzaConfigPanel();

        Button btnPanel = window.getBtnPanel();
        btnPanel.addActionDoneBtn(e->doneBtn());
        btnPanel.addActionCloseBtn(e->close());

        MyMenuBar menuBar = window.getMyMenuBar();
        menuBar.addActionCloseItem(e->close());
        menuBar.addSaveAction(e->save());
        menuBar.addReadAction(e->read());
    }

    /**
     * Save the pizza that has been ordered
     */
    private void doneBtn(){
        this.pizza = new Pizza(this.pizzaConfigPanel.getPrice(), this.pizzaConfigPanel.getPizzaSize(), this.pizzaConfigPanel.getPizzaTopping());
        List<Pizza> pizzaList = new ArrayList<>();
        if(this.getPizzaConfigPanel().getPrice() == 0){
            completeOrder();
        }else {
            int confirmed = JOptionPane.showConfirmDialog(mainView,"Order amount to pay: " + this.pizzaConfigPanel.getPrice(), "Order", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION){
                pizzaList.add(pizza);
                this.order = new Order(pizzaList);
                JOptionPane.showMessageDialog(mainView," Order: "+order.getOrderId() +"\n" +pizza.print());
            }
        }
    }

    /**
     * Close the program after the confirmation of exit
     */
    public void close(){
        int confirmed = JOptionPane.showConfirmDialog(mainView,"Are you sure you want to exit?", "Exit",JOptionPane.YES_NO_OPTION);
        if(confirmed == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Save the order you just created
     */
    public void save() {
        if (this.getPizzaConfigPanel().getPrice() == 0) {
            completeOrder();
        } else {
            dataStorage.writeOrder(this.order);
        }
    }

    /**
     * Read the saved pizza
     */
    public void read() {
        if(this.getPizzaConfigPanel().getPrice() == 0){
            completeOrder();
        }else{
            Order order = dataStorage.readOrder();
            System.out.println(order.toString());
            JOptionPane.showMessageDialog(mainView," Order "+order.getOrderId() +"\n" +pizza.print());
        }
    }

    /**
     * If you click save or read without creating the order, this message will pop up
     */
    private static void completeOrder(){
        JOptionPane.showMessageDialog(mainView, "Please create your pizza first :)");
    }

    /**
     * @return pizzaConfigPanel
     */
    public PizzaConfigPanel getPizzaConfigPanel() {
        return pizzaConfigPanel;
    }
}

