package view;

import  javax.swing.*;
import  java.awt.*;

public class TutorialSwing extends JFrame {

	public TutorialSwing() {

		super("ⓘ Tutorial");
		setSize(new Dimension(700,300));

		JPanel pan = new JPanel();
		FlowLayout bl = new FlowLayout(FlowLayout.CENTER);
		pan.setLayout(bl);

		JPanel pan1 = new JPanel();
		FlowLayout bl1 = new FlowLayout(FlowLayout.LEFT);
		pan.setLayout(bl1);

		JLabel lab = new JLabel("Ajouter / Supprimer un produit au stock");
		JLabel lab1 = new JLabel("- Ajouter nom du produit String dans le champ textuel Product.");
		JLabel lab2 = new JLabel("- Ajouter prix du produit Float dans le champ textuel Price.");
		JLabel lab3 = new JLabel("- Ajouter quantité du produit Int dans le champ textuel Quantity.");
		JLabel lab4 = new JLabel("- Chaque produit est ajoutée dans l’historique du stock.");
		JLabel lab5 = new JLabel("- Pour supprimer un produit, cliquer sur le produit dans l’historique du stock puis appuyer sur Delete.");
		JLabel lab6 = new JLabel("- Champ en vert : respect des types en entrée.");
		JLabel lab7 = new JLabel("- Champ en rouge : non respect des types en entrée.");

		pan.add(lab);
		pan1.add(lab1);
		pan1.add(lab2);
		pan1.add(lab3);
		pan1.add(lab4);
		pan1.add(lab5);
		pan1.add(lab6);
		pan1.add(lab7);

		setContentPane(pan);
		setVisible(true);

		setContentPane(pan1);
		setVisible(true);

	}


}
