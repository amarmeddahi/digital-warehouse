package view.menu;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class OrderMenuItemView extends JMenuItem{
	
	/** Action pour accéder aux commandes */	
	private class ActionOrder implements ActionListener {
		private Container contenu;

		public ActionOrder(Container contenu) {
			this.contenu = contenu;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout)(contenu.getLayout());
			cl.show(contenu, "Order");
			//OrderSwing orderFrame = new OrderSwing();
	    		//orderFrame.createFrame(modele);
		}
	}
	
	public OrderMenuItemView(Container contenu) {
		super("Order");
		addActionListener(new ActionOrder(contenu));
	}
	
}
