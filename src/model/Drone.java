package model;
public class Drone implements Machine {
	
	/** la coordonnée */
	private Coordonnee C;
	
	/** l'état */
	private Condition condition;
	
	/** le nom  */
	private String name;
	
	/** la vitesse  */
	// private float vitesse;
	
	public Drone(String name) {
		
		this.C = new Coordonnee();
		this.condition = Condition.ATTENTE;
		this.name = name;
	}
	/** déplacer le drone.
	* @param destination la desination
	*/
	public void move(Coordonnee destination) {
		//TODO
		}
	
	/** Executer une action par la machine.
	*/
	public void executeTask(Order order) {
		//TODO
		}
		
	public String getName() {
		return this.name;
	}
	
	public String getCord() {
		return this.C.toString();
	}
	
	public Condition getState() {
		return this.condition;
	}
}
