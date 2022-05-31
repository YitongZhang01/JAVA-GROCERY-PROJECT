package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.IOException;

import model.Event;
import model.EventLog;
import model.ShoppingCart;

// create a receipt after checkout
public class ReceiptPanel extends JFrame {
    ShoppingCart shoppingCart;

    private final JLabel content;
    private final JLabel content1;


    // MODIFIES: this
    // EFFECTS:  initializes this receipt to the current shopping cart, then draws the JFrame window and input
    // the image as title.

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public ReceiptPanel(ShoppingCart shoppingCart) throws IOException {
        this.shoppingCart = shoppingCart;

        this.setLayout(new BorderLayout());
        this.setSize(400, 400);

        JPanel topPanel = new JPanel();
        JPanel botPanel = new JPanel();


        topPanel.setBounds(0, 0, 400, 40);
        ImageIcon bg =
                new ImageIcon(new ImageIcon("./data/Receipt.png").getImage().getScaledInstance(400,
                        80, Image.SCALE_DEFAULT));
        JLabel title = new JLabel(bg);
        topPanel.add(title);


        content = new JLabel(printOut());

        content1 = new JLabel("<html> <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>"
                + "Thank You for your purchase!</html>");

        botPanel.add(content, BorderLayout.CENTER);
        botPanel.add(content1, BorderLayout.CENTER);

        JSplitPane sp = new JSplitPane(SwingConstants.HORIZONTAL, topPanel, botPanel);
        this.add(sp, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event e : EventLog.getInstance()) {
                    System.out.println(e.toString());
                }
                System.exit(0);
            }
        });
    }


    // add elements specific to the receipt portion the project
    public String printOut() {
        String print = "";

        for (int i = 0; i < shoppingCart.getNewCart().size(); i++) {
            print += "\n" + shoppingCart.getNewCart().get(i).getName()
                    + "\t Quantity: " + shoppingCart.getNum().get(i);
        }
        print += " Total: " + shoppingCart.getTotal() + "\n";
        return print;
    }




}

