package view;
import  javax.swing.*;
import  java.awt.*;

public class ContactSwing extends JFrame {

    public ContactSwing() {

        super("Contacts");
        setSize(new Dimension(500,300));

        JPanel pan = new JPanel();
        FlowLayout bl = new FlowLayout(FlowLayout.CENTER);
        pan.setLayout(bl);
        
        JLabel lab = new JLabel("Team developers\n");
        
        JLabel lab1 = new JLabel("BOUTIYARZIST Younes <younes.boutiyarzist@etu.toulouse-inp.fr>");
        JLabel lab2 = new JLabel("BROUSSEAU Valérian <valerian.brousseau@etu.toulouse-inp.fr> ");
        JLabel lab3 = new JLabel("BROUTIN Clément <clement.broutin@etu.toulouse-inp.fr>");
        JLabel lab4 = new JLabel("CAZANOVE Cédric <cedric.cazanove@etu.toulouse-inp.fr>");
        JLabel lab5 = new JLabel("MEDDAHI Amar <amar.meddahi@etu.toulouse-inp.fr>");
        JLabel lab6 = new JLabel("MD SUBHI Suhairi <mohamadsuhairi.mdsubhi@etu.toulouse-inp.fr>");
        JLabel lab7 = new JLabel("PHILIPPE Gaston <gaston.philippe@etu.toulouse-inp.fr>");
        
        pan.add(lab);
        pan.add(lab1);
        pan.add(lab2);
        pan.add(lab3);
        pan.add(lab4);
        pan.add(lab5);
        pan.add(lab6);
        pan.add(lab7);

        setContentPane(pan);
        setVisible(true);

    }

}
