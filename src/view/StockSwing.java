package view;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

import controller.StockController;
import model.ModeleWarehouse;
import model.Product;

import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.JPanel;

/** Programmation de la gestion des stocks de l'entrepôt */

public class StockSwing extends JPanel {
	
	private ModeleWarehouse warehouse;	// le modèle de l'entrepôt

	/** Construire la fenêtre des commandes */
	public StockSwing(ModeleWarehouse warehouse) {

		// Initialiser avec le modèle de l'entrepôt
		this.warehouse = warehouse;

		//Définir le gestionnaire de placement
		setLayout(new BorderLayout());
		
		//Definir le controlleur
		StockController stockControl = new StockController(warehouse);

		//Le champ d'ajout au nord
		JPanel champAjoutStock = stockControl.getChampAjout();
		this.add(champAjoutStock, BorderLayout.NORTH);

		//La vu sur le stock au centre
		JPanel vueStock = stockControl.getTable();
		this.add(vueStock, BorderLayout.CENTER);

		//les boutons au sud
		JPanel boutons = stockControl.getBouton();
		this.add(boutons, BorderLayout.SOUTH);
	}
}
