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
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.google.gson.Gson;
import java.lang.System;
import java.io.BufferedReader;

public class InitControllerVariante {

    public static final String txt = ".txt";

    public static final String json = ".json";

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

    private List<Product> products;

    private List<Order> orders;

    public InitControllerVariante() {
	this.gson = new Gson();
	File saveDir = new File(path);
	if (! saveDir.exists()) {
	    saveDir.mkdir();
	}
	try {
	    this.products = initProduct();
	} catch (IOException e) {
	    System.out.println("Erreur d'initailisation de Product");
	}
    }

    private static List<Product> initProduct() throws IOException{
	List<Product> list = new ArrayList<Product>();
	File directory = new File(path + productDir);
	if (! directory.exists()) {
	    directory.mkdir();
	} else {
	    File[] files = directory.listFiles();
	    for (int i = 0; i < files.length; ++i) {
		FileReader reader = new FileReader(files[i]);
		list.add(gson.fromJson(reader, Product.class));
		reader.close();
		files[i].delete();
	    }
	}
	return list;
    }

    public Stock initStock() {
	Stock stock = new Stock();
	File directory = new File(path + stockDir);
	if (! directory.exists()) {
	    directory.mkdir();
	} else {
	    try {
		for (Product product : this.products) {
		    File file = new File(path + stockDir + product.getName() + txt);
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    stock.addProduct(product, Integer.parseInt(reader.readLine()));
		    reader.close();
		    file.delete();
		}
	    } catch (IOException e) {
		System.out.println("Erreur d'initialisation de Stock");
	    }
	}
	return stock;
    }

    public List<Order> initOrder() {
	List<Order> list = new ArrayList<Order>();
	try {
	    File directory = new File(path + orderDir);
	    if (! directory.exists()) {
		directory.mkdir();
	    } else {
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; ++i) {
		    FileReader reader = new FileReader(files[i]);
		    list.add(gson.fromJson(reader, OrderForSave.class).toOrder(this.products));
		    reader.close();
		    files[i].delete();
		}
		File file = new File(path + currentOrderNo + txt);
		if (file.exists()) {
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    Order.setCurrentOrderNo(Integer.parseInt(reader.readLine()));
		    reader.close();
		}
	    }
	} catch (IOException e) {
	    System.out.println("Erreur d'initialisation de Order");
	}
	this.orders = list;
	return list;
    }

    private List<Robot> initRobot() {
	List<Robot> list = new ArrayList<Robot>();
	try {
	    File directory = new File(path + robotDir);
	    if (! directory.exists()) {
		directory.mkdir();
	    } else {
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; ++i) {
		    FileReader reader = new FileReader(files[i]);
		    list.add(gson.fromJson(reader, RobotForSave.class).toRobot());
		    reader.close();
		    files[i].delete();
		}
	    }
	} catch (IOException e) {
	    System.out.println("Erreur d'initialisation de Robot");
	}
	return list;
    }

    private List<Drone> initDrone() {
	List<Drone> list = new ArrayList<Drone>();
	try {
	    File directory = new File(path + droneDir);
	    if (! directory.exists()) {
		directory.mkdir();
	    } else {
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; ++i) {
		    FileReader reader = new FileReader(files[i]);
		    list.add(gson.fromJson(reader, Drone.class));
		    reader.close();
		    files[i].delete();
		}
	    }
	} catch (IOException e) {
	    System.out.println("Erreur d'initialisation de Drone");
	}
	return list;
    }

    public ControleCenter initCenter() {
	ControleCenter center = new ControleCenter();
	List<Robot> robots = initRobot();
	for (Robot robot : robots) {
	    center.addRobot(robot);
	}
	List<Drone> drones = initDrone();
	for (Drone drone : drones) {
	    center.addDrone(drone);
	}
	return center;
    }

    private int initSizeGrid() {
	try {
	    File file = new File(path + sizeGrid + txt);
	    if (file.exists()) {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		int size = Integer.parseInt(reader.readLine());
		reader.close();
		return size;
	    }
	} catch (IOException e) {
	    System.out.println("Erreur d'initialisation de la taille de Grid");
	}
	return 10;
    }

    public Grid initGrid() {
	Grid grid = new Grid(initSizeGrid());
	try {
	    File directory = new File(path + gridProductDir);
	    if (! directory.exists()) {
		directory.mkdirs();
	    } else {
		for (Product product : this.products) {
		    File file = new File(path + gridProductDir + product.getName() + json);
		    FileReader readerProd = new FileReader(file);
		    grid.addProduct(product, gson.fromJson(readerProd, Coordonnee.class));
		    readerProd.close();
		    file.delete();
		}
	    }
	    directory = new File(path + gridOrderDir);
	    if (! directory.exists()) {
		directory.mkdir();
	    } else {
		for (Order order : this.orders) {
		    File file = new File(path + gridOrderDir + order.getOrderNo() + json);
		    FileReader readerOrd = new FileReader(file);
		    grid.addOrder(order, gson.fromJson(readerOrd, Coordonnee.class));
		    readerOrd.close();
		    file.delete();
		}
	    }
	} catch (IOException e) {
	    System.out.println("Erreur d'initialisation de Grid");
	}
	return grid;
    }
}
