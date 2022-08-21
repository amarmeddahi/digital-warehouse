package view;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

import model.ModeleWarehouse;
import model.ModeleWarehouseV1;
import model.Stock;
import model.Order;
import controller.OrderController;

import java.awt.event.*;
import java.util.*;

/** Programmation de la gestion des commandes de l'entrepôt */

public class OrderSwing extends JPanel {

	private ModeleWarehouseV1 modele;// le modèle de l'entrepôt

	private Stock stock; 

	private JPanel nord, sud;

	/** Construire la fenêtre des commandes */
	public OrderSwing(ModeleWarehouseV1 modele) {

		//Definir le modele de l'entrepot
		this.modele = modele;

		//Définir le gestionnaire de placement
		this.setLayout(new BorderLayout());
		nord = new JPanel();
		nord.setLayout(new BorderLayout());
		this.add(nord, BorderLayout.NORTH);
		sud = new JPanel();
		sud.setLayout(new BorderLayout());
		this.add(sud,BorderLayout.SOUTH);

		//Definir le controlleur
		OrderController orderControl = new OrderController(modele);

		//Le champ d'ajout d'un produit au panier
		JPanel champAjoutPanier = orderControl.getChampAjout();
		nord.add(champAjoutPanier, BorderLayout.NORTH);

		//La vu sur le panier
		JPanel panier = orderControl.getTable();
		nord.add(panier, BorderLayout.SOUTH);

		//Le champ d'ajout des informations de la commande
		JPanel champAddOrder = orderControl.getChampAddOrder();
		sud.add(champAddOrder, BorderLayout.NORTH);

		//La vu sur toutes les commandes passés
		JPanel allOrder = orderControl.getAllOrder();
		sud.add(allOrder, BorderLayout.SOUTH);
	}

}
