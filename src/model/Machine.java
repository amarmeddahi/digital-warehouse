package model;
public interface Machine {
	
	//Modeliser l'état des machines
	enum Condition {ATTENTE, ENCOURS, RETOUR};
	
	/** Bouger la machine vers la destination.
	* @param destination la desination
	*/
	void move(Coordonnee destination);
	
	/** Executer une action par la machine.
	*/
	void executeTask(Order order);
}
