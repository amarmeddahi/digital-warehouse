package view.menu;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class WarehouseMenuView extends JMenu{
	
	private final JMenuItem menuOrder;
	private final JMenuItem menuStock;
	private final JMenuItem menuEmployee;
	private final JMenuItem menuView;
	
	
	public WarehouseMenuView(Container contenu) {

		super("Warehouse");
		
		this.menuStock = new StockMenuItemView(contenu);
		this.menuOrder = new OrderMenuItemView(contenu);
		this.menuView = new ViewMenuItemView(contenu);
		this.menuEmployee = new EmployeeMenuItemView(contenu);
		
		this.add(menuOrder);
		this.add(menuStock);
		this.add(menuEmployee);
		this.add(menuView);

	}
	
}
