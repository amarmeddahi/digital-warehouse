package model;
import java.util.*;

import exception.DeliveredException;
import model.orderState.Register;
import model.orderState.State;

/** This class symbolize an order with the different
 *  informations which are important.
 */
public class Order {

	/** The number of last order created. */
	static int currentOrderNo = 0;

	/** The ID of the order. */
	private int orderNo;
	/** The state of the order. */
	private State orderState;
	/** The date of creation of the order. */
	private Date orderDate;
	/** The date of shipping of the order. */
	private Date shippingDate;
	/** The address where the order must be shipped. */
	private float[] shippingAddress = new float[2];
	/** The list of product of the order. */
	private List<LineItem> basket;
	/** The price of the order. */
	private float price;

	/** Construct the order giving an ID 
	 *  and increment currentOrderNo for the
	 *  next order.
	 */
	public Order(float[] address){
		currentOrderNo++;
		this.orderNo = currentOrderNo;
		this.orderState = new Register();
		this.basket = new ArrayList<LineItem>();
		this.price = 0;
		this.shippingAddress = address;
		this.orderDate = new Date();
	}

	public static void setCurrentOrderNo(int n) {
		currentOrderNo = n;
	}

	public static int getCurrentOrderNo() {
		return currentOrderNo;
	}

	public void setOrderNo(int n) {
		this.orderNo = n;
	}

	public void setOrderDate(Date date) {
		this.orderDate = date;
	}

	public void setShippingDate(Date date) {
		this.shippingDate = date;
	}

	public void setShippingAddress(float[] coord) {
		this.shippingAddress[1] = coord[1];
		this.shippingAddress[2] = coord[2];
	}

	public void setShippingAddress(float x, float y) {
		this.shippingAddress[1] = x;
		this.shippingAddress[2] = y;
	}

	public void addInBasket(LineItem item) {
		this.basket.add(item);
		this.price += item.getPrice();
	}

	public void removeFromBasket(LineItem item) {
		this.basket.remove(item);
		this.price -= item.getPrice();
	}

	public int getOrderNo() {
		return this.orderNo;
	}

	public State getOrderState() {
		return this.orderState;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public Date getShippingDate() {
		return this.shippingDate;
	}

	public float[] getShippingAddress() {
		return this.shippingAddress;
	}

	public List<LineItem> getBasket() {
		List<LineItem> bask = this.basket;
		return bask;
	}

	public float getPrice() {
		return this.price;
	}

	/** Change to the next state of the order.
	 */
	public void changeStatus() {
		try {
			this.orderState = this.orderState.handle();
		} catch (DeliveredException e) {
			System.out.println("Commande déjà livrée");
		}
	}

	public void setBasket(List<LineItem> liste) {
		this.basket = liste;
	}

	public String toString() {
                String str = orderNo + " - " + orderState + " - " + orderDate + " - " + shippingDate + " - Address : ( " + shippingAddress[0] + ", " + shippingAddress[1] + ")\n";
                str = str + "\n Cart : \n";
                for (LineItem item : basket) {
                        str = str + item + " - ";
                }
                return str;
        }

}
