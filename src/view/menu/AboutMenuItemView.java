package view.menu;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class AboutMenuItemView extends JMenuItem {

	/** Action pour accéder aux stocks */
	private class ActionAbout implements ActionListener {

		public ActionAbout() {
		}
		@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Warehouse ® - Java / gson \n         VERSION 3.0");
			}
	}

	public AboutMenuItemView() {
		super("About");
		addActionListener(new ActionAbout());
	}

}


