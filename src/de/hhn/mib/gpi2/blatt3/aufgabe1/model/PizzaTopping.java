package de.hhn.mib.gpi2.blatt3.aufgabe1.model;

/**
 * @author Alin Gavrila
 * @version 1.0
 */
public enum PizzaTopping {
    TOMATO("Tomato"),
    CHEESE("Cheese"),
    SALAMI("Salami"),
    HAM("Ham"),
    ANANAS("Ananas"),
    VEGETABLES("Vegetables"),
    SEAFOOD("Seafood"),
    NUTELLA("Nutella"),
    SOUR_CREAM("Sour_Cream");

    private String topping;

    PizzaTopping(String topping) {
        this.topping = topping;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    @Override
    public String toString() {
        return topping;
    }
}
