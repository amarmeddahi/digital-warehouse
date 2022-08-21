package model;
public class Product {
    
    /** le nom */
    private String name;

    /** le prix */
    private float price;

    /** le constructeur */
    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    /** Un constructeur par défaut */
    public Product() {
        this.name = "NULL";
        this.price = (float) 0;
    }

    /** Obtenir le nom d'un produit
     * @return le nom du produit
     */
    public String getName() {
        return this.name;
    }

    public float getPrice() {
	return this.price;
    }

    @Override
    public String toString() {
          return this.name + " - price : " + this.price + " €";
    }
}
