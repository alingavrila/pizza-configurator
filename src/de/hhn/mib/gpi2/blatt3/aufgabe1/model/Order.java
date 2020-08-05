package de.hhn.mib.gpi2.blatt3.aufgabe1.model;

import java.io.Serializable;
import java.util.*;

/**
 * @author Alin Gavrila
 * @version 1.0
 */
public class Order implements Serializable{

    private long orderId;
    private List<Pizza> pizzas;
    Random random = new Random();

    public Order(List<Pizza> pizzas) {
        this.orderId = 1+random.nextInt(10000);
        this.pizzas = pizzas;
    }

    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }
    public void remove(Pizza pizza){
        this.pizzas.remove(pizza);
    }

    public long getOrderId() {
        return orderId;
    }
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                Objects.equals(pizzas, order.pizzas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, pizzas);
    }

    @Override
    public String toString() {
        return getOrderId()+";"+getPizzas();
    }
}
