package de.hhn.mib.gpi2.blatt3.aufgabe1.model;

/**
 * @author Alin Gavrila
 * @version 1.0
 */
public enum PizzaSize {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large"),
    EXTRA_LARGE("Extra_Large");

    private String size;

    PizzaSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size;
    }

}
