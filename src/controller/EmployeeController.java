package controller;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Robot;
import model.Drone;
import model.ControleCenter;

import javax.swing.JPanel;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/** Programmation de la gestion des machines de l'entrepôt */

public class EmployeeController extends JPanel {

	//Le modele de stock de l'entrepot
	private ControleCenter employee;

	//Le panel correspondant au champ textuel situé en haut
	private final JPanel champAjout = new JPanel(new FlowLayout());

	//Un timer pour reactualiser les data
	private Timer myTimer = new Timer();

	//Le panel correspondant a la vue sur le stock
	private final JPanel panelEmployee = new JPanel();
	private JTable tableEmployee;
	private Object[][] data;
	private EmployeeDynamicModel employeeModel;

	//Le panel correspondant au bouton en bas
	private final JPanel boutons = new JPanel();

	/** Bouton pour ajouter un robot */
	private final JButton boutonAddRobot = new JButton("Add Robot");
	
		/** Bouton pour ajouter un drone */
	private final JButton boutonAddDrone = new JButton("Add Drone");


	/** Pour saisir les champs pour ajouter un produit */
	//private final JLabel nomId = new JLabel("Id :");
	//private final JTextField champId = new JTextField(25);
	
	//private final JLabel prixProduit = new JLabel("Price :");
	//private final JTextField champPrix = new JTextField(10);
	//private final JLabel quantiteProduit = new JLabel("Quantity :");
	//private final JTextField champQuantite = new JTextField(10);

	/** L'action qui consiste à ajouter un robot */
	private final ActionListener actionAddRobot = new ActionAddRobot();

	/** L'action qui consiste à ajouter un drone */
	private final ActionListener actionAddDrone = new ActionAddDrone();	
	

	/** Construire les controleurs */
	public EmployeeController(ControleCenter employee) {

		this.employee = employee;

		//La partie champ en haut
		//champAjout.add(nomId);
		//champAjout.add(champId);
		
		//champAjout.add(prixProduit);
		//champAjout.add(champPrix);
		//champAjout.add(quantiteProduit);
		//champAjout.add(champQuantite);
		champAjout.add(boutonAddRobot);
		champAjout.add(boutonAddDrone);

		//La partie centrale
		data = employee.getAllEmployee();
		employeeModel = new EmployeeDynamicModel(data);
		tableEmployee = new JTable(employeeModel);
		tableEmployee.setAutoCreateRowSorter(true);
		JScrollPane sp = new JScrollPane(tableEmployee);
		panelEmployee.add(sp);


		// Construire le contrôleur (gestion des événements)
		boutonAddRobot.addActionListener(this.actionAddRobot);
		boutonAddDrone.addActionListener(this.actionAddDrone);

		myTimer.schedule(task,1000,1000);

	}

	/** Recuperer le JPanel des champs */
	public JPanel getChampAjout() {
		return champAjout;
	}

	/** Recuperer le JPanel de la table de stock */
	public JPanel getTable() {
		return panelEmployee;
	}

	/** Recuperer le JPanel des boutons */
	public JPanel getBouton() {
		return boutons;
	}

	/** Tache du timer */
	public TimerTask task = new TimerTask() {
		public void run() {
			data = employee.getAllEmployee();
			employeeModel.setData(data);
		}
	};

	/** Action d'ajouter un robot */
	private class ActionAddRobot implements ActionListener {
		@Override
			public void actionPerformed(ActionEvent e) {
				//String nom = champId.getText();
				
				String nom = employee.getTotalRobot() + "R";
				//Si un nom a ete ajouté, cest bon sinon cest rouge
				//if (nom.isEmpty()) {
					//champId.setBackground(Color.RED);
					//System.out.println("toto");
				//} else {
					//champId.setBackground(Color.GREEN);
				
				    employee.addRobot(new Robot(nom));
				    data = employee.getAllEmployee();
				    employeeModel.setData(data);
				    //champId.setText("");
				    //champId.setBackground(Color.WHITE);
				//}
			}
	}
	
    /** Action d'ajouter un drone */
	private class ActionAddDrone implements ActionListener {
		@Override
			public void actionPerformed(ActionEvent e) {
				//String nom = champId.getText();
				String nom = employee.getTotalDrone() + "D";
				//Si un nom a ete ajouté, cest bon sinon cest rouge
				//if (nom.isEmpty()) {
					//champId.setBackground(Color.RED);
				//} else {
					//champId.setBackground(Color.GREEN);
				    employee.addDrone(new Drone(nom));
				    data = employee.getAllEmployee();
				    employeeModel.setData(data);
				    //champId.setText("");
				    //champId.setBackground(Color.WHITE);
			    }
			
	}

}
