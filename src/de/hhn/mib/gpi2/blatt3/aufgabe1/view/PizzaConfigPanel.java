package de.hhn.mib.gpi2.blatt3.aufgabe1.view;

import de.hhn.mib.gpi2.blatt3.aufgabe1.model.PizzaSize;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.PizzaTopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alin Gavrila
 * @version 1.0
 */
public class PizzaConfigPanel extends JPanel{

    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JLabel label;
    private JTextField priceTextField;

    private ActionListener listener = new PriceListener();

    private PizzaSize[] pizzaSizes = PizzaSize.values();
    private JComboBox<PizzaSize> pizzaSizeJComboBox = new JComboBox<>(pizzaSizes);

    private List<ToppingCheckBox> pizzaToppingList = new ArrayList<>(PizzaTopping.values().length);

    private int totalPrice = 0;
    private int sizePrice = 0;
    private int toppingPrice = 0;


    public PizzaConfigPanel(){
        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        createTitle();
        createComboBox();
        createCheckBox();
        createPrice();
        createImage();

//      Title
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0,0,10,40);  //top padding
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(label, gridBagConstraints);

//      Combobow
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(40,20,10,160);  //top padding
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(northPanel, gridBagConstraints);

//      Checkbox
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(-30,20,10,0);  //top padding
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(centerPanel, gridBagConstraints);

//      Image
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(-10,0,10,0);  //top padding
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        add(eastPanel, gridBagConstraints);

//        add(eastPanel, BorderLayout.EAST);
        gridBagConstraints.ipady = 0;       //reset to default
        gridBagConstraints.weighty = 1.0;   //request any extra vertical space
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END; //bottom of space
        gridBagConstraints.insets = new Insets(-50,0,0,100);  //top padding
        gridBagConstraints.gridx = 0;       // column
        gridBagConstraints.gridy = 3;       //third row
        add(southPanel, gridBagConstraints);
    }

    /**
     * titel for the app
     */
    public void createTitle(){
        label = new JLabel("Pizza App:)");
        label.setFont(new Font("American Typewriter", Font.BOLD, 25));
        label.setForeground(Color.decode("#0287ed"));
    }

    /**
     * creates the combobox for the pizza
     */
    public void createComboBox(){
        pizzaSizeJComboBox.setModel(new DefaultComboBoxModel<>(PizzaSize.values()));
        pizzaSizeJComboBox.addActionListener(listener);
        northPanel.setLayout(new BorderLayout());
        northPanel.add(new JLabel("Pizza size: "), BorderLayout.WEST);
        northPanel.add(pizzaSizeJComboBox);
    }

    /**
     * checkbox for chosing the toppings
     */
    private void createCheckBox(){
        centerPanel.setLayout(new GridLayout(5,2));
        centerPanel.add(new JLabel("Toppings: "), BorderLayout.CENTER);

        for (PizzaTopping pizzaTopping :PizzaTopping.values()) {
            ToppingCheckBox checkBox = new ToppingCheckBox(pizzaTopping);
            pizzaToppingList.add(checkBox);
            centerPanel.add(checkBox);
            checkBox.addActionListener(listener);
        }
    }

    /**
     * creates the price box
     */
    private void createPrice(){
        southPanel.add(new JLabel("Total price: "));
        priceTextField = new JTextField(7);
        priceTextField.setFont(new Font("SERIF",Font.BOLD,15));
        priceTextField.setEditable(false);
        priceTextField.setForeground(Color.BLACK);
        priceTextField.setBackground(southPanel.getBackground());
        priceTextField.setHorizontalAlignment(SwingConstants.CENTER);
        southPanel.add(priceTextField);
        priceTextField.setText("0");
    }

    /**
     * image uploaded from browse
     */
    private void createImage(){
        ImageIcon imageIcon = new ImageIcon("src\\de\\hhn\\mib\\gpi2\\blatt3\\aufgabe1\\img\\pizza.jpg");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(250,250, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        eastPanel.add(new JLabel("", imageIcon, SwingConstants.LEFT));
    }

    /**
     * convert the value from cent to euro + cent
     * @param value value
     * @return price
     */
    private String convertCent(int value){
        int cents = value % 100;
        int euro = (value - cents) / 100;

        return "€"+euro+"."+cents;
    }

    /**
     * class for chosing the pizza size + toppings
     * the pizza size has it's own values.
     * for every chosen topping, the price get higher with 50 cents
     */
    private class PriceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String size = pizzaSizeJComboBox.getSelectedItem().toString();

            if (size.equals("Small")) {
                sizePrice = 500;
            }else if(size.equals("Medium")){
                sizePrice = 600;
            }else if(size.equals("Large")){
                sizePrice = 800;
            }else if(size.equals("Extra_Large")){
                sizePrice = 1100;
            }

            for (ToppingCheckBox topping:pizzaToppingList) {
                if(topping.isSelected())
                    toppingPrice += 50;
            }

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    totalPrice = sizePrice + toppingPrice;
                    priceTextField.setText(convertCent(totalPrice));
                }
            });
        }
    }

    public int getPrice(){
        return totalPrice;
    }

    public PizzaSize getPizzaSize(){
        return pizzaSizes [pizzaSizeJComboBox.getSelectedIndex()];
    }

    public List<PizzaTopping> getPizzaTopping(){
        List<PizzaTopping> topping = new ArrayList<>();
        for (ToppingCheckBox toppingCheckBox: pizzaToppingList)
            if(toppingCheckBox.isSelected()) {
                topping.add(toppingCheckBox.getPizzaTopping());
            }
        return topping;
    }
}
