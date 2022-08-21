package view;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

import controller.EmployeeController;
import model.Robot;
import model.Drone;
import model.ControleCenter;

import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.JPanel;

/** Programmation de la gestion des machines de l'entrepôt */

public class EmployeeSwing extends JPanel {

	private ControleCenter employee;	// le modèle de l'entrepôt

	/** Construire la fenêtre des commandes */
	public EmployeeSwing(ControleCenter employee) {

		// Initialiser avec le modèle de l'entrepôt
		this.employee = employee;

		//Définir le gestionnaire de placement
		setLayout(new BorderLayout());
		
		//Definir le controlleur
		EmployeeController employeeControl = new EmployeeController(employee);

		//Le champ d'ajout au nord
		JPanel champAjoutEmployee = employeeControl.getChampAjout();
		this.add(champAjoutEmployee, BorderLayout.NORTH);

		//La vu sur le stock au centre
		JPanel vueEmployee = employeeControl.getTable();
		this.add(vueEmployee, BorderLayout.CENTER);

		//les boutons au sud
		JPanel boutons = employeeControl.getBouton();
		this.add(boutons, BorderLayout.SOUTH);
	}
}
