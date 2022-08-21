package controller;

import model.ModeleWarehouseV1;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class CloseWindowListener implements WindowListener {

    private ModeleWarehouseV1 warehouse;

    public CloseWindowListener(ModeleWarehouseV1 warehouse) {
	this.warehouse = warehouse;
    }

    public void windowClosing(WindowEvent event) {
	SaveControllerVariante save = new SaveControllerVariante();
	save.saveStock(this.warehouse.getStock());
	save.saveOrder(this.warehouse.getOrders());
	save.saveGrid(this.warehouse.getGrid());
	save.saveCenter(this.warehouse.getCenter());
	System.exit(0);
    }
    
    public void windowOpened(WindowEvent event) {}
    public void windowClosed(WindowEvent event) {}
    public void windowIconified(WindowEvent event) {}
    public void windowDeiconified(WindowEvent event) {}
    public void windowActivated(WindowEvent event) {}
    public void windowDeactivated(WindowEvent event) {}

}
