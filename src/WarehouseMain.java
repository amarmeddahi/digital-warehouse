
import java.awt.*;

import model.ModeleWarehouseV1;
import view.PrincipalWindowView;
import controller.InitControllerVariante;

/** Programmation de la gestion d'un entrepôt employeeisé avec une interface graphique Swing.
  */

public class WarehouseMain {

// La méthode principale
// ---------------------

	public static void main(String[] args) {
		
		int warehouseSize = 10;

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ModeleWarehouseV1 modele = new ModeleWarehouseV1(warehouseSize);
				InitControllerVariante init = new InitControllerVariante();
				modele.setStock(init.initStock());
				modele.setOrders(init.initOrder());
				modele.setGrid(init.initGrid());
				modele.setCenter(init.initCenter());
				new PrincipalWindowView(modele);
			}
		});
	}

}
