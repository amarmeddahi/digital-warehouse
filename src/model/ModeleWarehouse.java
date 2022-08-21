package model;
public interface ModeleWarehouse {
	
    /** Ajouter un produit à l'entrepôt
     * @param produit à ajouter
     * @param quantite quantité du produit à ajouter
     */
    public void addProduct(Product p, int quantite);

    /** Retirer un produit de l'entrepôt
     * @param name le nom
     */
    public void removeProduct(String name);

    /** Obtenir la quantité d'un produit de l'entrepôt
     * @param name le nom
     * @return la quantité du produit
     */
    public int getQuantityProduct(String nameProduct);

    /** Obtenir le stock du warehouse */
    public Stock getStock();

 /** Ajouter une machine à l'entrepôt
     * @param name le nom
     * @param model le model de  machine
     */
    public void addMachine(String name, String model);


    /** Obtenir le Centre de controle du warehouse */
    public ControleCenter getCenter();

}
