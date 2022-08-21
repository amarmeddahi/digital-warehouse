package view;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.*;
import model.ModeleWarehouse;
import model.ModeleWarehouseV1;
import view.menu.MenuBarView;
import controller.CloseWindowListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PrincipalWindowView extends JFrame{
	
	private ModeleWarehouseV1 modele;	// le modèle de l'entrepôt
	
	private JMenuBar menuBar;

	/** Les différentes pages */
	private JPanel stock, order, employee, welcome, help;

	public PrincipalWindowView(ModeleWarehouseV1 modele) {
	
		//Définir la fenêtre principale	
		super("Warehouse");
		this.setLocation(100, 200);
	
		//Les différentes panes
		this.modele = modele;
		order = new OrderSwing(this.modele);
		employee = new EmployeeSwing(this.modele.getCenter());
		stock = new StockSwing(this.modele);
		welcome = new WelcomeSwing();
		help = new HelpSwing();
		
		//Définir le gestionnaire de placement
		Container contenu = this.getContentPane();
		contenu.setLayout(new CardLayout());
		contenu.add("Welcome", welcome);
		contenu.add("Stock", stock);
		contenu.add("Order", order);
		contenu.add("Employee", employee);
		contenu.add("Help", help);
        
		//une barre de menu
		this.setJMenuBar(new MenuBarView(contenu));
		
		//Définir le gestionnaire de placement
		welcome.setLayout(new BorderLayout());
		
        try {
            BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "/images/warehouse-logo.png"));
            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);
		    welcome.add(label, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		this.pack();
		this.setVisible(true);
		this.addWindowListener(new CloseWindowListener(this.modele));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}

