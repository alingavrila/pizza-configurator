package de.hhn.mib.gpi2.blatt3.aufgabe1.model;

import java.io.Serializable;
import java.util.*;

/**
 * @author Alin Gavrila
 * @version 1.0
 */
public class Pizza implements Serializable{

    private int price;
    private PizzaSize size;
    private List<PizzaTopping> pizzaToppings;

    public Pizza(int price, PizzaSize size, List<PizzaTopping> pizzaToppings) {
        this.price = price;
        this.size = size;
        this.pizzaToppings = pizzaToppings;
    }

    public int getPrice() {
        return price;
    }

    public PizzaSize getSize() {
        return size;
    }

    public List<PizzaTopping> getPizzaToppings() {
        return pizzaToppings;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public void setPizzaToppings(List<PizzaTopping> pizzaToppings) {
        this.pizzaToppings = pizzaToppings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return price == pizza.price &&
                size == pizza.size &&
                Objects.equals(pizzaToppings, pizza.pizzaToppings);
    }

    public String print(){
        return "Size: "+getSize().toString().replace("_"," ")+"\n"
                +"Toppings: "+getPizzaToppings().toString().replace("[","").replace("]","").replace("_"," ")
                +"\n"+"******************" +"\n"
                +"*   Price: "+getPrice()+"   *"+"\n"
                +"******************";
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, size, pizzaToppings);
    }

    @Override
    public String toString() {
        return getSize()+";"+getPizzaToppings()+";"+getPrice();
    }
}
