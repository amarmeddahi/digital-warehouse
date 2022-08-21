package model;
import java.util.*;

public class ModeleWarehouseV1 implements ModeleWarehouse {

	/** le stock associé à l'entrepôt */
	private Stock stock;

	/** le centre de controle associé à l'entrepôt */
	private ControleCenter center;

	/** le nom de l'entrepôt */
	private String name;

	/** la localisation de l'entrepôt */
	private String location;

	/** les commandes. */
	private List<Order> orders;

	/** La taille de grille (supposé grille est carré) de l'entrepôt */
	private int size;

	/** La grille de l'entrepôt */
	public static Grid grid;

	/** Coordonnee case vide */
	private Coordonnee emptyCasePosition;

	/** le constructeur */
	public ModeleWarehouseV1(int size) {
		this.grid = (ModeleWarehouseV1.grid == null) ? new Grid(size) : ModeleWarehouseV1.grid;
		this.stock = new Stock();
		this.center = new ControleCenter();
		this.orders = new ArrayList<Order>();
	}

	/** Ajouter un produit
	 * @param product produit à ajouter
	 */
	public void addProduct(Product product, int quantite) {
		stock.addProduct(product, quantite);
		grid.addProduct(product);
	}

	/** Retirer un produit
	 * @param name le nom
	 */
	public void removeProduct(String name) {
		grid.removeProduct(stock.getThisProduct(name));
		stock.removeProduct(name);
	}

	/** Obtenir le centre de controle du warehouse */
	public ControleCenter getCenter() {
		return this.center;
	}

	/** Ajouter une machine à l'entrepôt
	 * @param name le nom
	 * @param model le model de  machine
	 */
	public void addMachine(String name, String model) {
		if (model.equals("robot")) {
			center.addRobot(new Robot(name));
		} else if (model.equals("drone")) {
			center.addDrone(new Drone(name));
		} 
	}

	/** Obtenir la quantité d'un produit
	 * @param name le nom
	 * @return la quantité
	 */
	public int getQuantityProduct(String nameProduct) {
		return stock.getQuantityProduct(nameProduct);
	}

	/** Obtenir le stock du warehouse */
	public Stock getStock() {
		return this.stock;
	}

	/** Creer et ajouter une commande. */
	public void addOrder(Order order) {
		this.grid.addOrder(order);
		this.orders.add(order);
		center.getFreeRobot(order);
	}

	/** Retirer une commande
	 * @param index la position de l'order dans liste
	 */
	public void removeOrder(int index) {
		grid.removeOrder(orders.get(index));
		orders.remove(index);
	}

	/** Obtenir la liste des commandes. */
	public List<Order> getOrders() {
		return this.orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}       

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setCenter(ControleCenter center) {
		this.center = center;
	}

	public void setGrid(Grid grid) {
	    this.grid = grid;
	}

	public Grid getGrid() {
	    return this.grid;
	}
}
