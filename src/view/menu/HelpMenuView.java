package view.menu;

import java.awt.Container;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class HelpMenuView extends JMenu{
	
	private final JMenuItem menuAbout = new AboutMenuItemView();
	private final JMenuItem menuHelp;

	
	public HelpMenuView(Container contenu) {
		super("?");
		this.menuHelp = new HelpMenuItemView(contenu);
		this.add(menuHelp);
		this.add(menuAbout);
	}
	
}
