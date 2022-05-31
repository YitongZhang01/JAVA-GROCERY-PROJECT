package ui;

import model.Items;
import model.ShoppingCart;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Main shopping cart panel
public class MainPanel extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/shoppingcart.json ";

    private ShoppingCart shoppingCart;
    private String input;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private final JButton addButton;
    private final JButton checkOutButton;
    private final JButton loadButton;
    private final JButton saveButton;
    private final JLabel info;
    private int index;
    private final JList jlist;





    public static void main(String[] args) {
        new MainPanel();
    }


    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this shopping cart will operate, and populates the tools to be used
    //           to process the shopping cart
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public MainPanel() {
        shoppingCart = new ShoppingCart("Erica", 0.0);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        //Set the display size
        this.setMinimumSize(new Dimension(400, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create the Panels
        JPanel mainPanel = new JPanel();
        JPanel subPanel = new JPanel();


        //set and add main panels
        this.setTitle("Welcome to TT's Grocery store!");
        add(mainPanel);


        //set split panel

        JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, mainPanel, subPanel);
        sl.setResizeWeight(0.5);
        add(sl, BorderLayout.CENTER);





        // MODIFIES: this
        // EFFECTS:  customizes the button used for different purpose
        addButton = new JButton(new AddAction());
        addButton.addActionListener(this);
        loadButton = new JButton(new LoadAction());
        loadButton.addActionListener(this);
        checkOutButton = new JButton(new CheckOutAction());
        checkOutButton.addActionListener(this);
        saveButton = new JButton(new SaveAction());
        saveButton.addActionListener(this);

        info = new JLabel("Account name：" + shoppingCart.getCustomerName()
                + "\n Shopping cart current total: " + shoppingCart.getTotal());


        subPanel.add(addButton);
        subPanel.add(loadButton);
        subPanel.add(checkOutButton);
        subPanel.add(saveButton);

        mainPanel.add(info);


        //Create JList
        jlist = new JList();

        DefaultListModel dlm = new DefaultListModel();

        dlm.addElement("1. \t Apple \t ->\t$2.99");
        dlm.addElement("2. \t Banana \t ->\t$1.39");
        dlm.addElement("3. \t Watermelon \t ->\t$4.99");
        dlm.addElement("4. \t Peach \t ->\t$5.69");
        dlm.addElement("5. \t Orange \t ->\t$3.89");
        jlist.setModel(dlm);

        index = jlist.getSelectedIndex();

        mainPanel.add(jlist);


        //select different options
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        jlist.setVisibleRowCount(-1);

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    // MODIFIES: this
    // EFFECTS:  press different buttons for different panel
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            new AddAction();
        } else if (e.getSource() == saveButton) {
            new SaveAction();
            this.dispose();

            new MainGUI();
            setVisible(true);


        } else if (e.getSource() == loadButton) {
            new LoadAction();
        } else if (e.getSource() == checkOutButton) {
            new CheckOutAction();
            this.dispose();

        }

    }


    // MODIFIES: this
    // EFFECTS:  Add action to add item to shopping cart


    private class AddAction extends AbstractAction {
        public AddAction() {
            super("Add Item");
        }

        @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
        @Override
        public void actionPerformed(ActionEvent ab) {
            String name;
            index = jlist.getSelectedIndex();

            int i = -1;
            while (i < 0) {
                input = JOptionPane.showInputDialog("Please enter the quantities: ");
                if (input.length() > 0) {
                    i++;
                } else {
                    System.out.println("Enter the quantities");
                }
            }
            switch (index) {
                case 0:
                    name = "Apple";
                    break;
                case 1:
                    name = "Banana";
                    break;
                case 2:
                    name = "Watermelon";
                    break;
                case 3:
                    name = "Peach";
                    break;
                case 4:
                    name = "Orange";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + index);
            }

            shoppingCart.addItem(new Items(name, Integer.parseInt(input)));
            JOptionPane.showMessageDialog(null,"Add successfully");
            info.setText("\n Shopping cart current total: " + shoppingCart.getTotal());
            repaint();


        }
    }


    // MODIFIES: this
    // EFFECTS:  Load item to shopping cart

    private class LoadAction extends AbstractAction {
        public LoadAction() {
            super("Load Shopping Cart");
        }

        @Override
        public void actionPerformed(ActionEvent el) {
            try {
                shoppingCart = jsonReader.read();
                info.setText("Loaded " + shoppingCart.getCustomerName() + " from " + JSON_STORE);
            } catch (IOException e) {
                info.setText("Unable to read from file: " + JSON_STORE);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS:  Checkout action to check out the shopping cart


    private class CheckOutAction extends AbstractAction {
        public CheckOutAction() {
            super("Check out");
        }

        // MODIFIES: this
        // EFFECTS:  Prompt user the option to choose if they have a membership, and apply the discount
        // and display the total
        @Override
        public void actionPerformed(ActionEvent e) {
            int answer = JOptionPane.showConfirmDialog(null,
                    "Do you have Membership?", null, JOptionPane.YES_NO_OPTION);

            if (answer == 0) {
                shoppingCart.applyDiscount();
                JOptionPane.showMessageDialog(null, "20% discount applies! Your total price is:" + "\n"
                        + shoppingCart.getTotal());
            } else {

                JOptionPane.showMessageDialog(null, "Sorry, no membership discount will apply...\nYour total price is:"
                                        + shoppingCart.getTotal());
            }
            try {
                new ReceiptPanel(shoppingCart);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS:  save action to save shopping cart

    private class SaveAction extends AbstractAction {
        public SaveAction() {
            super("Save Shopping Cart");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(shoppingCart);
                jsonWriter.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }

        }


    }






}