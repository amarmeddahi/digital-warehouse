package controller;

import model.LineItem;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;
import model.LineItem;
import model.Order;

public class OrderDynamicModel extends AbstractTableModel {

	private final String[] headers = {"Product", "Quantity"};

	private List<LineItem> basket;

	public OrderDynamicModel(List<LineItem> basket) {
		super();
		this.basket = basket;
	}

	@Override
		public int getRowCount() {
			return basket.size();
		}

	@Override
		public int getColumnCount() {
			return 2;
		}

	@Override
		public String getColumnName(int columnIndex) {
			return headers[columnIndex];
		}

	@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex==0) {
				return basket.get(rowIndex).getProduct();
			}
			else {
				return basket.get(rowIndex).getAmount();
			}

		}

	public void afficher() {
		for (int i = 0; i < basket.size(); i++) {
			System.out.println(basket.get(i).getProduct() + "(x" + basket.get(i).getAmount() + ")");
		}
		System.out.println();
	} 

	public void setData(List<LineItem> basket) {
		this.basket = basket;
		fireTableDataChanged();
	}

	public void addData(LineItem newProduct) {
		this.basket.add(newProduct);
		fireTableDataChanged();
	}

	public void removeData(LineItem newProduct) {
		//this.basket.add(newProduct);
		//fireTableDataChanged();
	}

	public List<LineItem> getBasket() {
		return basket;
	}
}
