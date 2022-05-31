package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterQ extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField field;


    public EnterQ() {
        super("Add Quantity");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Add");
        btn.setActionCommand("Add");
        btn.addActionListener(this);
        label = new JLabel("quantity:");
        field = new JTextField(5);

        add(label);
        add(field);
        add(btn);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);



        // add buttons and elements specific to the receipt portion of your project
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            field.getText();
        }

    }

}
