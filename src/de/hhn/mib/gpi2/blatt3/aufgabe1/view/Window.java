package de.hhn.mib.gpi2.blatt3.aufgabe1.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alin Gavrila
 * @version 1.0
 */
public class Window extends JFrame {

    private PizzaConfigPanel pizzaConfigPanel;
    private Button btnPanel;
    private MyMenuBar menuBar;

    /**
     * constructor creates window + add components
     */
    public Window(){
        this.pizzaConfigPanel = new PizzaConfigPanel();
        this.btnPanel = new Button();
        this.menuBar = new MyMenuBar();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //buttons
        constraints.insets = new Insets(10,20,20,0);
        constraints.gridx = 0;
        constraints.gridy = 2;

        this.getContentPane().setLayout(new GridBagLayout());
        setVisible(true);
        this.getContentPane().add(pizzaConfigPanel);
        this.getContentPane().add(btnPanel, constraints);

        setJMenuBar(menuBar);
        setTitle("Pizza App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(850,850);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * getter pizzaConfigPanel
     * @return pizzaConfigPanel
     */
    public PizzaConfigPanel getPizzaConfigPanel() {
        return pizzaConfigPanel;
    }

    public Button getBtnPanel() {
        return btnPanel;
    }

    public MyMenuBar getMyMenuBar() {
        return menuBar;
    }
}
