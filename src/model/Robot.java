package model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.LinkedList;

import exception.IllegalOrderStateException;
import model.Case.CaseState;

public class Robot implements Machine {
	
	class MoveTask extends TimerTask{

		@Override
		public void run() {	
			int x = 0;
			int y = 0;
			if (destination.getX() > curPosition.getX()) {
				x = 1;
			} else if (destination.getX() < curPosition.getX()){
				x = -1;
			}		
			if (destination.getY() > curPosition.getY()) {
				y = 1;
			} else if (destination.getY() < curPosition.getY()){
				y = -1;
			}		
			if (true) {//ModeleWarehouseV1.grid.getCaseState(x,y) == CaseState.EMPTY ) { //rajouter la condition que la case soit libre pour aller sur la case
				curPosition.translater(x,y);
			}
			if (destination.getX() == curPosition.getX() && destination.getY() == curPosition.getY()) {
				
				condition = Condition.ATTENTE;
				if (order != null) {
					Coordonnee orderPosition = ModeleWarehouseV1.grid.getOrderPosition(order);
					
					//aller vers la case de la commande
					if (productsPosition.size() == 0 ){
						move(orderPosition);
						order.changeStatus();
						order = null;
					} else {
						move(productsPosition.poll());
					}
				} else if (curPosition.getX() != 0.0 && curPosition.getY() != 0.0) {
					move(new Coordonnee(0.0, 0.0));
				} else {
					myTimer.cancel();
				}
			}
		}
		
	}
	
	/** la coordonnée */
	private Coordonnee curPosition;
	
	/** l'état */
	private Condition condition;
	
	/** le nom  */
	private String name;
	
	/** la destination  */
	private Coordonnee destination;

	/** la liste des produits */
    private LinkedList<Coordonnee> productsPosition;
    
    /** order en cours */
    private Order order;
	
	private Timer myTimer = new Timer();
	
	
	public Robot(String name) {
		
		this.curPosition = new Coordonnee();
		this.condition = Condition.ATTENTE;
		//this.destination = new Coordonnee();
		productsPosition = new LinkedList<Coordonnee>();
		this.name = name;
	}

	/** déplacer le robot.
	* @param destination la desination
	*/
	public void move(Coordonnee destination) {
		this.destination = destination;
		myTimer.schedule(new MoveTask(), 1000,1000);
	}
	
	/** Executer une action par la machine.
	*/
	public void executeTask(Order order) {

		if(order.getOrderState().toInt() != 1) {
			new IllegalOrderStateException("La commande n'est pas dans l'état de préparation");
		}
		
		this.order = order;
		this.condition = Condition.ENCOURS;
		//aller récupérer le produit 
		for (LineItem l : order.getBasket()){
			productsPosition.add(ModeleWarehouseV1.grid.getProductPosition(l.getProduct()));
			
		}
		move(productsPosition.poll());
	}
		
	public String getName() {
		return this.name;
	}
	
	public String getCord() {
		return this.curPosition.toString();
	}
	
	public boolean IsFree() {
		return (getState() == Condition.ATTENTE);
	}
	public Condition getState() {
		return this.condition;
	}

	public Coordonnee getCoordonnee() {
	    return this.curPosition;
	}

	public void setCoordonnee(Coordonnee cord) {
	    this.curPosition = cord;
	}

	public void setState(Condition state) {
	    this.condition = state;
	}
	public Coordonnee getDestination() {
	    return this.destination;
	}
	public LinkedList<Coordonnee> getProductsPosition() {
	    return this.productsPosition;
	}
	public void setDestination(Coordonnee destination) {
	    this.destination = destination;
	}
	public void setProductsPosition(LinkedList<Coordonnee> list) {
	    this.productsPosition = list;
	}
}
