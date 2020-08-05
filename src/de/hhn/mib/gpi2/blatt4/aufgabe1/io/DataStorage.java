package de.hhn.mib.gpi2.blatt4.aufgabe1.io;

import de.hhn.mib.gpi2.blatt3.aufgabe1.model.Order;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.Pizza;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.PizzaSize;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.PizzaTopping;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alin Gavrila
 * @version 1.1
 */
public class DataStorage{

    private static final String STORAGE_FORMAT = "binary";

    /**
     * if the constant is binary => writeOrderBin
     * else if the constant is text => writeOrderCSV
     * @param order order
     */
    public void writeOrder(Order order){
        if(STORAGE_FORMAT == "binary"){
            writeOrderBin(order);
        }else if(STORAGE_FORMAT == "text") {
            writeOrderCSV(order);
        }
    }

    /**
     * if the constant is binary => readOrderBin
     * else if the constant is text => readOrderCSV
     * @return
     */
    public Order readOrder(){
        if(STORAGE_FORMAT == "binary"){
            return readOrderBin();
        }else if(STORAGE_FORMAT == "text") {
            return readOrderCSV();
        }
        return null;
    }

    /**
     * create the write file and the order will be written
     * @param order order
     */
    public void writeOrderCSV(Order order){
        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\HHN\\Sem 2\\GPI2\\Arbeitsblatt3\\src\\de\\hhn\\mib\\gpi2\\blatt4\\aufgabe1\\io\\bestellung.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(order);
            objectOut.close();
            System.out.println("The order was succesfully written to a file");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * read the order from the chosen file
     * @return the Order or null by exceptions
     */
    public Order readOrderCSV() {

        try {
            FileInputStream fileIn = new FileInputStream("C:\\HHN\\Sem 2\\GPI2\\Arbeitsblatt3\\src\\de\\hhn\\mib\\gpi2\\blatt4\\aufgabe1\\io\\bestellung.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Order order = (Order) objectIn.readObject();
            objectIn.close();
            System.out.println("The Object has been read from the file");
            return order;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * creates the bin write file, splits the order and write the variables
     * @param order order
     */
    public void writeOrderBin(Order order){
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("C:\\HHN\\Sem 2\\GPI2\\Arbeitsblatt3\\src\\de\\hhn\\mib\\gpi2\\blatt4\\aufgabe1\\io\\bestellung.bin"))) {
            String s[] = order.toString().replace("[", "").replace("]", "").split(";");
            out.writeLong(Long.parseLong(s[0]));
            out.writeUTF(s[1]);
            out.writeUTF(s[2]);
            out.writeInt(Integer.parseInt(s[3]));

        }catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read the variable from the file
     * @return the Order or null
     */
    public Order readOrderBin() {
        try (DataInputStream in = new DataInputStream(new FileInputStream("C:\\HHN\\Sem 2\\GPI2\\Arbeitsblatt3\\src\\de\\hhn\\mib\\gpi2\\blatt4\\aufgabe1\\io\\bestellung.bin"))) {
            long orderId;
            String size;
            String toppings;
            int price;

            while (true){
                orderId = in.readLong();
                size = in.readUTF();
                toppings = in.readUTF();
                price = in.readInt();

                PizzaSize pizzaSize = PizzaSize.valueOf(size.toUpperCase());

                PizzaTopping pizzaTopping;
                List<PizzaTopping> pizzaToppingList = new ArrayList<>();
                String []split = toppings.split(", ");
                for (String s:split) {
                    pizzaTopping = PizzaTopping.valueOf(s.toUpperCase());
                    pizzaToppingList.add(pizzaTopping);
                }
                Pizza pizza = new Pizza(price, pizzaSize, pizzaToppingList);
                List<Pizza> pizzaList = new ArrayList<>();
                pizzaList.add(pizza);
                Order order = new Order(pizzaList);
                order.setOrderId(orderId);
                return order;
            }

        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}