package view.menu;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class HelpMenuItemView extends JMenuItem {

	/** Action pour accéder aux stocks */
	private class ActionHelp implements ActionListener {
		
		private Container contenu;

		public ActionHelp(Container contenu) {
			this.contenu = contenu;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout)(contenu.getLayout());
			cl.show(contenu, "Help");
		}
	}
	
	public HelpMenuItemView(Container contenu) {
		super("Help");
		addActionListener(new ActionHelp(contenu));
	}
	
}

