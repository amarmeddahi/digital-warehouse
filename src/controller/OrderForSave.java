package controller;

import model.Order;
import model.orderState.State;
import model.Product;
import model.LineItem;
import exception.ProductAbsentException;
import java.util.*;

class OrderForSave {

    protected int orderNo;
    protected int state;
    protected Date orderDate;
    protected Date shippingDate;
    protected float[] shippingAddress = new float[2];
    protected Map<String, Integer> basket;

    OrderForSave(Order order) {
	this.state = order.getOrderState().toInt();
	this.orderDate = order.getOrderDate();
	this.shippingDate = order.getShippingDate();
	this.shippingAddress = order.getShippingAddress();
	this.basket = constructBasket(order.getBasket());
	this.orderNo = order.getOrderNo();
    }

    Order toOrder(List<Product> products) {
	Order ret = new Order(this.shippingAddress);
	ret.setOrderDate(this.orderDate);
	ret.setShippingDate(this.shippingDate);
	this.toOrderBasket(ret, products);
	ret.setOrderNo(this.orderNo);
	for (int i = 0; i < this.state; i++) {
	    ret.changeStatus();
	}
	return ret;
    }

    private static Map<String, Integer> constructBasket(List<LineItem> basket) {
	Map<String, Integer> map = new HashMap<String, Integer>();
	for (LineItem line : basket) {
	    map.put(line.getProduct().getName(), line.getAmount());
	}
	return map;
    }

    private void toOrderBasket(Order order, List<Product> products) {
	try {
	    for (Map.Entry<String, Integer> entry : this.basket.entrySet()) {
		Product prod = findProduct(products, entry.getKey());    
		LineItem item = new LineItem(prod, entry.getValue());
		order.addInBasket(item);
	    }
	} catch (ProductAbsentException e) {
	    System.out.println("le produit recherché est absent");
	}
    }

    private static Product findProduct(List<Product> products, String name) {
	int i = 0;
	while ((i < products.size()) && (! name.equals(products.get(i).getName()))) {
	    i++;
	}
	if (i == products.size()) {
	    throw new ProductAbsentException("le produit recherché est absent");
	} else {
	    return products.get(i);
	}
    }

}   
