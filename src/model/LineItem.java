package model;

public class LineItem {

	private Product product;
	private int amount;

	public LineItem(Product product, int amount) {
		this.product = product;
		this.amount = amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return this.product;
	}

	public int getAmount() {
		return this.amount;
	}

	public float getPrice() {
		return this.amount*this.product.getPrice();
	}

	public String toString() {
		return product + "( x " + amount + ")";
	}

}
