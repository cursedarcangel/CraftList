package com.cursedarcangel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    static JTextField wantCraft = new JTextField();
    static JTextField howMany = new JTextField();
    CraftList craftlist = new CraftList();
    static JLabel result = new JLabel("");

    public static void main(String[] args) {

        JFrame frame = new JFrame("CraftList");
        JPanel panel = new JPanel();

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel whatCraft = new JLabel("What you want to craft:");
        whatCraft.setBounds(10, 20, 180, 25);
        JLabel manyHow = new JLabel("How many to craft:");
        manyHow.setBounds(10, 60, 180, 25);

        wantCraft.setBounds(200,20,165,25);
        howMany.setBounds(200, 60, 165, 25);

        JButton calculate = new JButton("Calculate");
        calculate.addActionListener(new GUI());
        calculate.setBounds(150, 150, 100, 50);

        result.setBounds(100, 200, 250, 100);

        panel.add(whatCraft);
        panel.add(wantCraft);
        panel.add(manyHow);
        panel.add(howMany);
        panel.add(result);
        panel.add(calculate);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String craft = wantCraft.getText();
        Integer amount = 1;
        try {
            amount = Integer.valueOf(howMany.getText());
        } catch (NumberFormatException exception) {
            System.out.println();
        }
        result.setText(craftlist.parseJson(craft, amount));
    }
}