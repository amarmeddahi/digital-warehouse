package test;
import model.Product;
import model.Stock;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestStock {

	private Stock stock;
	private Product pomme, poire;

	@Before public void setUp() {
		//Construire le stock
		stock = new Stock();
		poire = new Product("Poire", (float) 3.50);
		pomme = new Product("Pomme", (float) 2.50);
		stock.addProduct(poire, 2); 
		stock.addProduct(pomme, 3); 
	}

	@Test public void testAjouter() {
		assertEquals("Produit absent", this.stock.isPresent("Pomme"), true);
	}

	@Test public void testSupprimer() {
		stock.removeProduct("Pomme");
		assertEquals("Produit toujours présent", ! this.stock.isPresent("Pomme"), true);
	}

	@Test public void testQuantite() {
		assertEquals("Erreur sur la quantité", this.stock.getQuantityProduct("Pomme") == 3, true);
	}
}

