package de.hhn.mib.gpi2.blatt3.aufgabe1.view;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.PizzaTopping;
import javax.swing.*;

/**
 * @author Alin Gavrila
 * @version 1.0
 */
public class ToppingCheckBox extends JCheckBox {

    private final PizzaTopping pizzaTopping;

    /**
     * enum topping tostring
     * @param pizzaTopping
     */
    public ToppingCheckBox(PizzaTopping pizzaTopping) {
        super(pizzaTopping.toString());
        this.pizzaTopping = pizzaTopping;
    }

    public PizzaTopping getPizzaTopping() {
        return pizzaTopping;
    }
}
