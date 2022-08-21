package view;
import java.awt.*;

import model.ModeleWarehouseV1;

/** Programmation de la gestion d'un entrepôt employeeisé avec une interface graphique Swing.
  */

public class WarehouseMain {

// La méthode principale
// ---------------------

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PrincipalWindowView(new ModeleWarehouseV1(10));
			}
		});
	}

}
