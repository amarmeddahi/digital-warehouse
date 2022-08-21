package model;
import java.util.*;

public class Stock {

	/** le stock <Produit || Quantite> */
	private Map<Product, Integer> stock; 

	/** Le nombre total de produit different dans l'entrepot */
	private int total = 0;


	/** le constructeur */
	public Stock() {
		stock = new HashMap<Product, Integer>();
	}

	/** Ajouter un produit
	 * @param product le produit
	 */
	public void addProduct(Product product, int quantity) {
		stock.put(product, quantity);
		total++;
	}

	/** Retirer un produit
	 * @param name le nom du produit
	 */
	public void removeProduct(String name) {
		Product toRemove = this.getThisProduct(name);
		stock.remove(toRemove);
		total--;
	}

	/** Obtenir la quantité d'un produit
	 * @param nameProduct le nom du produit
	 * @return la quantité du produit
	 */
	public int getQuantityProduct(String nameProduct) {
		Product p = this.getThisProduct(nameProduct);
		return stock.get(p);
	}

	/** Verifier la présence d'un produit dans le stock 
	 * @param produit le produit à vérifier
	 */
	public boolean isPresent(String nameProduct) {
		Product p = this.getThisProduct(nameProduct);
		return this.stock.containsKey(p);
	}

	/** Obtenir le produit dans le stock du nom nameProduct */
	public Product getThisProduct(String nameProduct) {
		for (Map.Entry<Product, Integer> entry : stock.entrySet()) {
			Product p = entry.getKey();
			if (nameProduct.equals(p.getName())) {
				return p;
			}
		}
		return new Product();
	}

	/** Obtenir un tableau de tous les produits et des quantites du stock */
	public Object[][] getAllProduct() {
		Object[][] allProduct = new Object[total][3];
		int i = 0;
		for (Map.Entry<Product, Integer> entry : stock.entrySet()) {
			allProduct[i][0] = entry.getKey().getName();
			allProduct[i][1] = entry.getKey().getPrice();
			allProduct[i][2] = entry.getValue();
			i++;
		}
		return allProduct;
	}

	public Map<Product, Integer> getStock() {
		return this.stock;
	}

	public void setQuantityProduct(String nameProduct, int quantite) {
		Product p = this.getThisProduct(nameProduct);
			this.stock.replace(p, quantite);
	}
}
