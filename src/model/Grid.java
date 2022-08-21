package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.GridFullException;
import model.Case.CaseState;

public class Grid {
    
	/** La taille de grille (supposé grille est carré) de l'entrepôt */
    private int size;
    
    /** La grille de l'entrepôt */
    private List<ArrayList<Case>> grid;
    
    /** accès à la position de produit plus rapide */
	private Map<Product, Coordonnee> gridStock;

    /** accès à la position de commande plus rapide */
	private Map<Order, Coordonnee> gridOrder;

    /** le constructeur pour le Grid */
    public Grid(int size) {
        this.size = size;
        this.grid = new ArrayList< ArrayList<Case> >();
    	for(int i = 0; i < size; i++) {
    		grid.add(i, new ArrayList<Case>());
            for(int j = 0; j < size; j++) {
                grid.get(i).add(j, new Case());
            }
    	}
        grid.get(0).get(0).setStation();
    	gridStock = new HashMap<Product, Coordonnee>();
        gridOrder = new HashMap<Order, Coordonnee>();
    }

    /** le constructeur pour le Grid */
    public void addProduct(Product product){
        Coordonnee currentPosition = getEmptyCasePosition();
        gridStock.put(product, currentPosition);
        Case currentCase = grid.get((int)currentPosition.getY()).get((int)currentPosition.getX());
        currentCase.setProduct(product);
    }

    public void addProduct(Product product, Coordonnee currentPosition){
        gridStock.put(product, currentPosition);
        Case currentCase = grid.get((int)currentPosition.getY()).get((int)currentPosition.getX());
        currentCase.setProduct(product);
    }


    public void removeProduct(Product product){
        Coordonnee productPosition = getProductPosition(product);
        gridStock.remove(product);
        Case productCase = grid.get((int)productPosition.getY()).get((int)productPosition.getX());
        productCase.removeProduct();
    }

    public void addOrder(Order order){
    	Coordonnee currentPosition = getEmptyCasePosition();
        gridOrder.put(order, currentPosition);
        Case currentCase = grid.get((int)currentPosition.getY()).get((int)currentPosition.getX());
        currentCase.setOrder(order);
    }
    
   public void addOrder(Order order, Coordonnee currentPosition){
        gridOrder.put(order, currentPosition);
        Case currentCase = grid.get((int)currentPosition.getY()).get((int)currentPosition.getX());
        currentCase.setOrder(order);
    }

    public void removeOrder(Order order){
        Coordonnee orderPosition = getOrderPosition(order);
        gridOrder.remove(order);
        Case orderCase = grid.get((int)orderPosition.getY()).get((int)orderPosition.getX());
        orderCase.removeOrder();
    }

    public Coordonnee getEmptyCasePosition() {
    	
    	for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(grid.get(i).get(j).getContentType() == Case.ContentType.EMPTY){
                	return new Coordonnee((float)j, (float)i);
                }
            }
    	}

        // si on arrive ici, il y a aucune case disponible
    	throw new GridFullException("Grid full. PLease try again later");
    }

    public Coordonnee getProductPosition(Product product){
		return gridStock.get(product);
    }

    public Coordonnee getOrderPosition(Order order){
		return gridOrder.get(order);
    }
    
    public CaseState getCaseState(int x, int y) {
    	return grid.get(y).get(x).getCaseState();
    }

    public Map<Product, Coordonnee> getProducts() {
    	return this.gridStock;
    }
    
    public Map<Order, Coordonnee> getOrders() {
    	return this.gridOrder;
    }
    
    public int getSize() {
    	return this.size;
    }
}
