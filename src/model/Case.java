package model;

import exception.IllegalCaseStateException;

public class Case {
    
    /** le type de contenu de la case */
	public static enum ContentType{ EMPTY, PRODUCT, ORDER , STATION};

    /** l'état de la case */
    public static enum CaseState{ EMPTY, OCCUPIED };

    /** le produit */
    private Product product;

    /** la commande */
    private Order order;

    /** le type de contenu de la case */
    private ContentType contentType;

    /** l'état de la case */
    private CaseState caseState;

    /** le constructeur par défaut */
    public Case() {
        this.product = null;
        this.order = null;
        this.contentType = ContentType.EMPTY;
        this.caseState = CaseState.EMPTY;
    }

    /** Obtenir le produit de la case
     * @return le produit de la case
     */
    public Product getProduct() {
        return this.product;
    }

    /** Obtenir la commande de la case
     * @return la commande de la case
     */
    public Order getOrder() {
        return this.order;
    }

    /** Obtenir le type de contenu de la case
     * @return le type de contenu de la case
     */
    public ContentType getContentType() {
        return this.contentType;
    }
    
    /** Obtenir l'état la case
     * @return le type de contenu de la case
     */
    public CaseState getCaseState() {
        return this.caseState;
    }

    /** Affecter un produit dans la case.
     *  @param product produit à affecter
     */
    public void setProduct(Product product) {
        if (this.contentType != ContentType.EMPTY){
            throw new IllegalCaseStateException("Product/order exist in this case!");
        }

        this.product = product;
        this.contentType = ContentType.PRODUCT;
    }
    
    /** Enlever le produit de la case.
     */
    public void removeProduct() {
    	if (this.contentType != ContentType.PRODUCT){
            throw new IllegalCaseStateException("No product exist in this case!");
        }
    	
    	this.product = null;
        this.contentType = ContentType.EMPTY;

    }

    /** Affecter une commande dans la case.
     *  @param product commande à affecter
     */
    public void setOrder(Order order) {
        if (this.contentType != ContentType.EMPTY){
            throw new IllegalCaseStateException("Product/order exist in this case!");
        }

        this.order = order;
        this.contentType = ContentType.ORDER;
    }
    
    /** Enlever la commande de la case.
     */
    public void removeOrder() {
    	if (this.contentType != ContentType.ORDER){
            throw new IllegalCaseStateException("No order exist in this case!");
        }
    	
    	this.order = null;
        this.contentType = ContentType.EMPTY;

    }
    
    public void setStation() {
    	if (this.contentType != ContentType.EMPTY){
            throw new IllegalCaseStateException("No order exist in this case!");
        }
        this.contentType = ContentType.STATION;
    }

    @Override
    public String toString() {
          return contentType.name();
    }
}
