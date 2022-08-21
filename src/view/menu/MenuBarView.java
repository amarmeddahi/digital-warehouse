package view.menu;

import java.awt.Container;

import javax.swing.JMenuBar;

public class MenuBarView extends JMenuBar{
	
	public MenuBarView (Container contenu) {
		super();
		this.add(new WarehouseMenuView(contenu));
		this.add(new HelpMenuView(contenu));
	}
}
