package ui;

import model.ShoppingCart;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Main user GUI to display the program.
public class MainGUI extends JFrame  {

    private static final String JSON_STORE = "./data/shoppingcart.json ";
    private ShoppingCart shoppingCart;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static void main(String[] args) {
        new MainGUI();
    }

    // MODIFIES: this
    // EFFECTS:  initializes a shopping cart

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public MainGUI() {
        shoppingCart = new ShoppingCart("Erica", 0.0);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        String input = JOptionPane.showInputDialog("\n Please choose an option:"
                + "\n 1. Start new shopping cart"
                + "\n 2. Load shopping cart "
                + "\n 3. Quit");

        int in = Integer.parseInt(input);

        switch (in) {
            case 1:
                MainPanel dp = new MainPanel();
                break;
            case 2:
                MainPanel ls = new MainPanel();
                try {
                    shoppingCart = jsonReader.read();
                    System.out.println("Loaded " + shoppingCart.getCustomerName() + " from " + JSON_STORE);
                } catch (IOException exx) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
                break;
            default:
                System.exit(0);

        }

    }

}

