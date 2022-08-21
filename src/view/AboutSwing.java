package view;

import  javax.swing.*;
import  java.awt.*;

public class AboutSwing extends JFrame {

    public AboutSwing() {

        super("About");
        setSize(new Dimension(200,200));

        JPanel pan = new JPanel();
        FlowLayout bl = new FlowLayout(FlowLayout.CENTER);
        pan.setLayout(bl);

        JLabel lab = new JLabel("Warehouse ® - Java / gson");
        JLabel lab1 = new JLabel("VERSION 2.0");
        pan.add(lab);
        pan.add(lab1);

        setContentPane(pan);
        setVisible(true);

    }

    public static void main(String args[]) {
        new AboutSwing();
    }
}
