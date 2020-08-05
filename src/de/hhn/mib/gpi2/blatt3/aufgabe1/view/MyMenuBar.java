package de.hhn.mib.gpi2.blatt3.aufgabe1.view;
import javax.swing.*;
import java.awt.event.*;

/**
 * @author Alin Gavrila
 * @version 1.0
 */
public class MyMenuBar extends JMenuBar{
    private JMenu file, order, help;
    private JMenuItem close, save, read;


    MyMenuBar() {

        //Menu items
        close = new JMenuItem("Close");
        save = new JMenuItem("Save");
        read = new JMenuItem("Read");

        //Menu files
        file = new JMenu("File");
        order = new JMenu("Order");
        help = new JMenu("Help");

        //Add item to menu
        file.add(close);
        order.add(save);
        order.add(read);

        //Add to menubar
        this.add(file);
        this.add(order);
        this.add(help);
    }

    /**
     * add action listener to close item
     * @param listener
     */
    public void addActionCloseItem(ActionListener listener){
        close.addActionListener(listener);
    }

    /**
     * add action listener to save
     * @param listener
     */
    public void addSaveAction(ActionListener listener){
        save.addActionListener(listener);
    }

    /**
     * add action listener to read
     * @param listener
     */
    public void addReadAction(ActionListener listener){
        read.addActionListener(listener);
    }

}