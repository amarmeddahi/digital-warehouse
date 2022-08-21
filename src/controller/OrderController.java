package controller;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Product;
import model.ModeleWarehouse;
import model.ModeleWarehouseV1;
import model.LineItem;
import model.Order;
import model.Stock;

import java.text.DateFormat;
import java.text.*;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.*;

/** Programmation de la gestion des stocks de l'entrepôt */

public class OrderController extends JPanel {

	//Le modele de l'entrepot
	private ModeleWarehouseV1 modele;

	//Le stock de l'entrepot
	private Stock stock;

	//Le panel correspondant au champ textuel situé en haut
	private final JPanel champAjoutOrder = new JPanel(new FlowLayout());

	//Le panel correspondant a la vue sur le stock
	private final JPanel panelOrder = new JPanel();
	private JTable tableOrder;
	private List<LineItem> basket;
	private OrderDynamicModel orderModel;

	/** Pour saisir les champs pour ajouter un produit */
	private final JLabel nomProduit = new JLabel("Name:");
	private final JTextField champProduit = new JTextField(25);
	private final JLabel quantiteProduit = new JLabel("Quantity:");
	private final JTextField champQuantite = new JTextField(10);

	/** Bouton pour ajouter des commandes */
	private final JButton boutonAdd = new JButton("Add");

	/** Bouton pour ajouter des commandes */
	private final JButton boutonDelete = new JButton("Delete");

	//Le panel correspondant au champ textuel Date De Livraison/Addresse/Bouton Add
	private final JPanel champAddOrder = new JPanel(new FlowLayout());
	private final JLabel shippingDate = new JLabel("Date of shipping :");
	private final JTextField textShipDate = new JTextField(25);
	private final JLabel address = new JLabel("Address :");
	private final JLabel xAddress = new JLabel("( x :");
	private final JTextField textX = new JTextField(10);
	private final JLabel yAddress = new JLabel(", y :");
	private final JTextField textY = new JTextField(10);
	private final JLabel endAddress = new JLabel(")");
	private final JButton boutonAddOrder = new JButton("Add");
	private final JButton boutonDeleteOrder = new JButton("Delete");

	//Le panel correspondant a la liste de toutes les commandes
	private JPanel allOrder = new JPanel();
	private List<Order> listOrder = new ArrayList<Order>();
	private ListModel listOrderModel;
	private JList<Order> jlistOrder = new JList();

	/** Construire les controleurs */
	public OrderController(ModeleWarehouseV1 modele) {
		this.modele = modele;
		this.stock = modele.getStock();

		//La partie où l'on finalise la commande
		champAddOrder.add(shippingDate);
		champAddOrder.add(textShipDate);
		champAddOrder.add(address);
		champAddOrder.add(xAddress);
		champAddOrder.add(textX);
		champAddOrder.add(yAddress);
		champAddOrder.add(textY);
		champAddOrder.add(endAddress);
		champAddOrder.add(boutonAddOrder);
		champAddOrder.add(boutonDeleteOrder);

		//La vue des commandes
		listOrder = modele.getOrders();
		jlistOrder.setModel(convert(listOrder));
		JScrollPane spList = new JScrollPane(jlistOrder);
		spList.setPreferredSize(new Dimension(800,100));
		allOrder.add(spList);

		//
		basket = new ArrayList<LineItem>();
		orderModel = new OrderDynamicModel(basket);
		tableOrder = new JTable(orderModel);
		tableOrder.setAutoCreateRowSorter(true);
		JScrollPane sp = new JScrollPane(tableOrder);
		panelOrder.add(sp);

		//
		champAjoutOrder.add(nomProduit);
		champAjoutOrder.add(champProduit);
		champAjoutOrder.add(quantiteProduit);
		champAjoutOrder.add(champQuantite);
		champAjoutOrder.add(boutonAdd);
		champAjoutOrder.add(boutonDelete);

		// Construire le contrôleur (gestion des événements)
		boutonAddOrder.addActionListener(new ActionAddOrder());
		boutonDeleteOrder.addActionListener(new ActionDeleteOrder());
		boutonAdd.addActionListener(new ActionAdd());
		boutonDelete.addActionListener(new ActionDelete());

	}

	/** Recuperer le JPanel de la vu sur les commandes */
	public JPanel getAllOrder() {
		return allOrder;
	}

	/** Recuperer le JPanel des champs de de texte pr finir la commande */
	public JPanel getChampAddOrder() {
		return champAddOrder;
	}

	/** Convertir une liste en listmodel */
	public DefaultListModel<Order> convert(List<Order> list) {
		DefaultListModel<Order> model = new DefaultListModel<Order>();
		for (Order o : list) {
			model.addElement(o);
		}
		return model;
	}

	/** Recuperer le JPanel des champs */
	public JPanel getChampAjout() {
		return champAjoutOrder;
	}

	/** Recuperer le JPanel de la table de stock */
	public JPanel getTable() {
		return panelOrder;
	}

	/** Methode qui ajoute un produit au panier */
	private class ActionAdd implements ActionListener {
		@Override
			public void actionPerformed(ActionEvent e) {
				Product p = new Product();
				String nom = champProduit.getText();
				//Si un nom a ete ajouté, cest bon sinon cest rouge
				if (nom.isEmpty() || !stock.isPresent(nom)) {
					champProduit.setBackground(Color.RED);
				} else {
					champProduit.setBackground(Color.GREEN);
				}
				float prix = (float) 0.0;
				int quantite = 0;
				try {
					quantite = (Integer) Integer.parseInt(champQuantite.getText());
					champQuantite.setBackground(Color.GREEN);

				} catch (NumberFormatException ex) { //change la couleur en rouge pour indiquer que ce n'est pas un entier
					champQuantite.setBackground(Color.RED);
				}
				try {
					if (stock.getQuantityProduct(nom) >= quantite) {
						orderModel.addData(new LineItem(stock.getThisProduct(nom), quantite));
						stock.setQuantityProduct(nom, stock.getQuantityProduct(nom) - quantite);
						champQuantite.setText("");
						champProduit.setText("");
						champQuantite.setBackground(Color.WHITE);
						champProduit.setBackground(Color.WHITE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Insufficient amount of product.");
						champQuantite.setText("");
						champProduit.setText("");
						champQuantite.setBackground(Color.WHITE);
						champProduit.setBackground(Color.WHITE);
					}
				} catch (NullPointerException ex) {
				}
			}
	}

	/** Action pour supprimer du panier */
	private class ActionDelete implements ActionListener {
		@Override
			public void actionPerformed(ActionEvent e) {
				//try {
				//	int[] selection = basket ...;
				//	for (int i = 0; i < selection.length; i++) {
				//		orderModel.removeData(new LineItem(stock.getThisProduct(nom), quantite));
				//	}
				//	basket = stock.getAllProduct();
				//	stockModel.setData(basket);
				//} catch (NullPointerException ex) {
				//}
			}
	}

	/** Ajouter une commande dans l'historique de toute les commandes */
	private class ActionAddOrder implements ActionListener {
		@Override
			public void actionPerformed(ActionEvent e) {
				DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);

				String nameShipDate = textShipDate.getText();
				Date shippingDate = new Date();
				try {
					shippingDate = new SimpleDateFormat("dd/MM/yyyy").parse(nameShipDate);
					textShipDate.setBackground(Color.GREEN);
				} catch (ParseException ex) {
					textShipDate.setBackground(Color.RED);
				}

				float address[] = new float[2];
				float x = (float) 0.0;
				float y = (float) 0.0;
				try {
					x = (float) Float.parseFloat(textX.getText());
					address[0] = x;
					textX.setBackground(Color.GREEN);
				} catch (NumberFormatException ex) {
					textX.setBackground(Color.RED);
				}

				try {
					y = (float) Float.parseFloat(textY.getText());
					address[1] = y;
					textY.setBackground(Color.GREEN);
				} catch (NumberFormatException ex) {
					textY.setBackground(Color.RED);
				}

				Order newOrder = new Order(address);
				newOrder.setShippingDate(shippingDate);
				newOrder.setBasket(orderModel.getBasket());
				int total = newOrder.getBasket().size();
				if (total > 0) {
					modele.addOrder(newOrder);
					jlistOrder.setModel(convert(listOrder));
					basket = new ArrayList<LineItem>();
					orderModel.setData(basket);
					textShipDate.setText("");
					textX.setText("");
					textY.setText("");
					textShipDate.setBackground(Color.WHITE);
					textX.setBackground(Color.WHITE);
					textY.setBackground(Color.WHITE);
				} else {
					JOptionPane.showMessageDialog(null, "There is no article in the cart.\n Please check your cart.");
					textShipDate.setText("");
					textX.setText("");
					textY.setText("");
					textShipDate.setBackground(Color.WHITE);
					textX.setBackground(Color.WHITE);
					textY.setBackground(Color.WHITE);

				}
			}
	}

	private class ActionDeleteOrder implements ActionListener {
		@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int i = jlistOrder.getSelectedIndex();
					modele.removeOrder(i);
					jlistOrder.setModel(convert(listOrder));
				} catch (NullPointerException ex) {
				}
			}
	}
}
