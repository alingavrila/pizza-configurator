package de.hhn.mib.gpi2.blatt3.aufgabe1.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @autor Alin Gavrila
 * @version 1.0
 */
public class Button extends JPanel {

    JButton doneBtn = new JButton("Done");
    JButton closeBtb = new JButton("Close");

    public Button() {
        this.setLayout(new FlowLayout());
        this.add(doneBtn);
        this.add(closeBtb);
    }

    /**
     * add action listener to doneBtn
     * @param l
     */
    public void addActionDoneBtn(ActionListener l) {
        doneBtn.addActionListener(l);
    }

    /**
     * add action listener to closeBtn
     * @param l
     */
    public void addActionCloseBtn(ActionListener l){
        closeBtb.addActionListener(l);
    }
}
