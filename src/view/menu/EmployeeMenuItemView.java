package view.menu;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class EmployeeMenuItemView extends JMenuItem{
	
	/** Action pour accéder aux employees */
	private class ActionEmployee implements ActionListener {
		private Container contenu;

		public ActionEmployee(Container contenu) {
			this.contenu = contenu;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout)(contenu.getLayout());
			cl.show(contenu, "Employee");
			//EmployeeSwing employeeFrame = new EmployeeSwing();
			//employeeFrame.createFrame(modele);
		}
	}
	
	public EmployeeMenuItemView(Container contenu) {
		super("Employee");
		addActionListener(new ActionEmployee(contenu));
	}
	
}
