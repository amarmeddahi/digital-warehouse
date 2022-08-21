package model;
/** les coordonnées des robots
 */
public class Coordonnee {
	
	/** abscisse */
	private double x;
			
	/** ordonnée */
	private double y;		

	/** Construire une Coordonnee à partir de son abscisse et de son ordonnée.
	 * @param vx abscisse
	 * @param vy ordonnée
	 */
	public Coordonnee(double vx, double vy) {
		this.x = vx;
		this.y = vy;
	}
	/** Construire une Coordonnee àpar défault en (0,0).

	 */
	public Coordonnee() {
		this.x = 0;
		this.y = 0;
	}
	/** Obtenir l'abscisse de la coordonnee.
	 * @return abscisse de la coordonnee
	 */
	public double getX() {
		return this.x;
	}

	/** Obtenir l'ordonnée de la coordonnee.
	 * @return ordonnée de la coordonnee
	 */
	public double getY() {
		return this.y;
	}

	/** Changer l'abscisse de la coordonnee.
	  * @param vx nouvelle abscisse
	  */
	public void setX(double vx) {
		this.x = vx;
	}

	/** Changer l'ordonnée de la coordonnee.
	  * @param vy nouvelle ordonnée
	  */
	public void setY(double vy) {
		this.y = vy;
	}

	/** Afficher la coordonnee. */
	public void afficher() {
		System.out.print("(" + this.x + ", " + this.y + ")");
	}

	/** Distance par rapport à une autre coordonnee.
	 * @param autre l'autre coordonnee
	 * @return distance entre this et autre
	 */
	public double distance(Coordonnee autre) {
		return Math.sqrt(Math.pow(autre.getX() - this.x, 2)
					+ Math.pow(autre.getY() - this.y, 2));
	}

   /** Translater la coordonnee.
	* @param dx déplacement suivant l'axe des X
	* @param dy déplacement suivant l'axe des Y
	*/
	public void translater(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}
	
		/** Afficher la coordonnee. */
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	
}
