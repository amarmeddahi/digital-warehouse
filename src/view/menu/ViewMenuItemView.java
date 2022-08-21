package view.menu;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ViewMenuItemView extends JMenuItem{
	
	/** Action pour accéder à l'entrepôt */
	private class ActionView implements ActionListener {
		private Container contenu;

		public ActionView(Container contenu) {
			this.contenu = contenu;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("ToDo");
		}
	}
	
	public ViewMenuItemView(Container contenu) {
		super("View");
		addActionListener(new ActionView(contenu));
	}
	
}
