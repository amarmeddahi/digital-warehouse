package controller;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

public class StockDynamicModel extends AbstractTableModel {

	private final String[] headers = {"Product", "Price", "Quantity"};

	private Object[][] data;

	private int total = 0;

	public StockDynamicModel(Object[][] data) {
		super();
		this.data =data;
	}

	@Override
		public int getRowCount() {
			return data.length;
		}

	@Override
		public int getColumnCount() {
			return 3;
		}

	@Override
		public String getColumnName(int columnIndex) {
			return headers[columnIndex];
		}

	@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}

	@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}

	public void afficher() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				System.out.print(data[i][j] + " ");
			}    
			System.out.println();
		}
	} 

	public void setData(Object[][] data) {
		this.data = data;
		fireTableDataChanged();
	}

	public void getDataOnRow(int row) {
		for (int j = 0; j < data[0].length; j++) {
			System.out.print(data[row][j] + " ");
		}    
		System.out.println();
	}
}
