package controller;

import model.Product;
import model.Stock;
import model.Order;
import model.Robot;
import model.Drone;
import model.ControleCenter;
import model.Grid;
import model.Coordonnee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.google.gson.Gson;
import java.lang.System;

public class SaveControllerVariante {

    public static final String json = ".json";

    public static final String txt = ".txt";

    public static final String path = System.getProperty("user.dir") + "/save/";

    public static final String productDir = "products/";

    public static final String stockDir = "stock/";

    public static final String orderDir = "orders/";

    public static final String currentOrderNo = "currentorderno";

    public static final String robotDir = "robots/";

    public static final String droneDir = "drones/";

    public static final String gridProductDir = "grid/products/";

    public static final String gridOrderDir = "grid/orders/";

    public static final String sizeGrid = "grid/size";

    private static Gson gson;

    private static int nbCase;

    public SaveControllerVariante() {
	this.gson = new Gson();
    }

    private void saveProduct(Product product) {
	nbCase = 0;
	try {
	    File file = new File(path + productDir + product.getName() + json);
	    FileWriter writer = new FileWriter(file);
	    writer.write(gson.toJson(product));
	    writer.close();
	} catch (IOException e) {
	    System.out.println("Erreur de sauvegarde de Product");
	}
    }

    private void saveQuantity(String name, int n) {
	try {
	    File file = new File(path + stockDir + name + txt);
	    FileWriter writer = new FileWriter(file);
	    String quant = Integer.toString(n);
	    writer.write(quant, 0, quant.length());
	    writer.close();
	} catch (IOException e) {
	    System.out.println("Erreur de sauvegarde de la quantité");
	}
    }

    public void saveStock(Stock stock) {
	for (Map.Entry<Product, Integer> entry : stock.getStock().entrySet()) {
	    this.saveProduct(entry.getKey());
	    this.saveQuantity(entry.getKey().getName(), entry.getValue());
	}
    }

    private void saveOrder(Order order) {
	try {
	    File file = new File(path + orderDir + order.getOrderNo() + json);
	    OrderForSave ofs = new OrderForSave(order);
	    FileWriter writer = new FileWriter(file);
	    writer.write(gson.toJson(ofs));
	    writer.close();
	    File fileNo = new File(path + currentOrderNo + txt);
	    FileWriter writer1 = new FileWriter(fileNo);
	    String current = Integer.toString(Order.getCurrentOrderNo());
	    writer1.write(current, 0, current.length());
	    writer1.close();
	} catch (IOException e) {
	    System.out.println("Erreur de sauvegarde de Order");
	}
    }

    public void saveOrder(List<Order> orders) {
	for (Order order : orders) {
	    this.saveOrder(order);
	}
    }

    private void saveRobot(Robot robot) {
	try {
	    File file = new File(path + robotDir + robot.getName() + json);
	    FileWriter writer = new FileWriter(file);
	    writer.write(gson.toJson(new RobotForSave(robot)));
	    writer.close();
	} catch (IOException e) {
	    System.out.println("Erreur de sauvegarde de Robot");
	}
    }

    private void saveDrone(Drone drone) {
	try {
	    File file = new File(path + droneDir + drone.getName() + json);
	    FileWriter writer = new FileWriter(file);
	    writer.write(gson.toJson(drone));
	    writer.close();
	} catch (IOException e) {
	    System.out.println("Erreur de sauvegarde de drone");
	}
    }

    public void saveCenter(ControleCenter center) {
	List<Robot> robots = center.getAllRobot();
	for (Robot robot : robots) {
	    saveRobot(robot);
	}
	List<Drone> drones = center.getAllDrone();
	for (Drone drone : drones) {
	    saveDrone(drone);
	}
    }

    private void saveCaseProduct(Product product, Coordonnee cord) throws IOException {
	File file = new File(path + gridProductDir + product.getName() + json);
	FileWriter writer = new FileWriter(file);
	writer.write(gson.toJson(cord));
	writer.close();
    }

    private void saveCaseOrder(Order order, Coordonnee cord) throws IOException {
	File file = new File(path + gridOrderDir + order.getOrderNo() + json);
	FileWriter writer = new FileWriter(file);
	writer.write(gson.toJson(cord));
	writer.close();
    }

    public void saveGrid(Grid grid) {
	try {
	    File file = new File(path + sizeGrid + txt);
	    FileWriter writer = new FileWriter(file);
	    writer.write(Integer.toString(grid.getSize()));
	    writer.close();
	    for (Map.Entry<Product, Coordonnee> entry : grid.getProducts().entrySet()) {
		saveCaseProduct(entry.getKey(), entry.getValue());
	    }
	    for (Map.Entry<Order, Coordonnee> entry : grid.getOrders().entrySet()) {
		saveCaseOrder(entry.getKey(), entry.getValue());
	    }
	} catch (IOException e) {
	    System.out.println("Erreur de sauvegarde de Grid");
	}
    }

}
