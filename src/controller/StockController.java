package controller;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.ModeleWarehouse;
import model.Product;
import model.Stock;

import javax.swing.JPanel;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.*;

/** Programmation de la gestion des stocks de l'entrepôt */

public class StockController extends JPanel {

	//Le modele de stock de l'entrepot
	private ModeleWarehouse warehouse;

	//Le modele de stock de l'entrepot
	private Stock stock;

	//Le panel correspondant au champ textuel situé en haut
	private final JPanel champAjout = new JPanel(new FlowLayout());

	//Le panel correspondant a la vue sur le stock
	private final JPanel panelStock = new JPanel();
	private JTable tableStock;
	private Object[][] data;
	private StockDynamicModel stockModel;

	//Un timer pour reactualiser les data
	private Timer myTimer = new Timer();

	//Le panel correspondant au bouton en bas
	private final JPanel boutons = new JPanel();

	/** Bouton pour ajouter du stock */
	private final JButton boutonAdd = new JButton("Add");

	/** Bouton pour supprimer un produit */
	private final JButton boutonDelete = new JButton("Delete");

	/** Pour saisir les champs pour ajouter un produit */
	private final JLabel nomProduit = new JLabel("Product :");
	private final JTextField champProduit = new JTextField(25);
	private final JLabel prixProduit = new JLabel("Price :");
	private final JTextField champPrix = new JTextField(10);
	private final JLabel quantiteProduit = new JLabel("Quantity :");
	private final JTextField champQuantite = new JTextField(10);

	/** L'action qui consiste à ajouter du stock */
	private final ActionListener actionAdd = new ActionAdd();
	/** L'action qui consiste à supprimer un produit */
	private final ActionListener actionDelete = new ActionDelete();

	/** Construire les controleurs */
	public StockController(ModeleWarehouse warehouse) {
		this.warehouse = warehouse;
		this.stock = warehouse.getStock();

		//La partie champ en haut
		champAjout.add(nomProduit);
		champAjout.add(champProduit);
		champAjout.add(prixProduit);
		champAjout.add(champPrix);
		champAjout.add(quantiteProduit);
		champAjout.add(champQuantite);
		champAjout.add(boutonAdd);

		//La partie centrale
		data = stock.getAllProduct();
		stockModel = new StockDynamicModel(data);
		tableStock = new JTable(stockModel);
		tableStock.setAutoCreateRowSorter(true);
		JScrollPane sp = new JScrollPane(tableStock);
		panelStock.add(sp);

		//La partie bouton en bas
		boutons.add(boutonDelete);

		// Construire le contrôleur (gestion des événements)
		boutonAdd.addActionListener(this.actionAdd);
		boutonAdd.addKeyListener(new KeyEnterAdd());
		boutonDelete.addActionListener(this.actionDelete);

		myTimer.schedule(task,1000,10000);
	}

	/** Recuperer le JPanel des champs */
	public JPanel getChampAjout() {
		return champAjout;
	}

	/** Recuperer le JPanel de la table de stock */
	public JPanel getTable() {
		return panelStock;
	}

	/** Recuperer le JPanel des boutons */
	public JPanel getBouton() {
		return boutons;
	}

	/** Methode qui ajoute un produit au stock */
	private void addToStock() {
		String nom = champProduit.getText();
		//Si un nom a ete ajouté, cest bon sinon cest rouge
		if (nom.isEmpty() || stock.isPresent(nom)) {
			champProduit.setBackground(Color.RED);
		} else {
			champProduit.setBackground(Color.GREEN);
		}
		float prix = (float) 0.0;
		int quantite = 0;
		try {
			prix = (Float) Float.parseFloat(champPrix.getText());
			champPrix.setBackground(Color.GREEN);
		} catch (NumberFormatException ex) { //Change la couleur en rouge pour indiquer que ce n'est pas un prix
			champPrix.setBackground(Color.RED);
		}
		try {
			quantite = (Integer) Integer.parseInt(champQuantite.getText());
			champQuantite.setBackground(Color.GREEN);
		} catch (NumberFormatException ex) { //change la couleur en rouge pour indiquer que ce n'est pas un entier
			champQuantite.setBackground(Color.RED);
		}
		if (prix > (float) 0 && quantite > 0 && (! stock.isPresent(nom))) {
			Product p = new Product(nom, prix);
			warehouse.addProduct(p, quantite);
			data = stock.getAllProduct();
			stockModel.setData(data);
			champProduit.setText("");
			champPrix.setText("");
			champQuantite.setText("");
			champProduit.setBackground(Color.WHITE);
			champPrix.setBackground(Color.WHITE);
			champQuantite.setBackground(Color.WHITE);
		}
	}

	/** Tache du timer */
	public TimerTask task = new TimerTask() {
		public void run() {
			//if (! tableStock.getSelectionModel().isSelectionEmpty()) {
				data = stock.getAllProduct();
				stockModel.setData(data);
			//} else {
			//	myTimer.cancel();
			//}
		}
	};

	/** Action d'ajouter du stock par la touche entrée */
	private class KeyEnterAdd implements KeyListener {
		public void keyTyped(KeyEvent e) {}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				addToStock();
			}
		}

		public void keyReleased(KeyEvent e) {}
	}

	/** Action d'ajouter du stock */
	private class ActionAdd implements ActionListener {
		@Override
			public void actionPerformed(ActionEvent e) {
				addToStock();
			}
	}

	/** Action pour supprimer un produit */
	private class ActionDelete implements ActionListener {
		@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int[] selection = tableStock.getSelectedRows();
					for (int i = 0; i < selection.length; i++) {
						warehouse.removeProduct((String) data[selection[i]][0]);
					}
					data = stock.getAllProduct();
					stockModel.setData(data);
				} catch (NullPointerException ex) {
				}
			}
	}
}
