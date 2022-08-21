package view.menu;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class StockMenuItemView extends JMenuItem{
	
	/** Action pour accéder aux stocks */
	private class ActionStock implements ActionListener {
		
		private Container contenu;

		public ActionStock(Container contenu) {
			this.contenu = contenu;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout)(contenu.getLayout());
			cl.show(contenu, "Stock");
		}
	}
	
	public StockMenuItemView(Container contenu) {
		super("Stock");
		addActionListener(new ActionStock(contenu));
	}
	
}
