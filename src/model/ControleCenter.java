package model;
import java.util.*;

public class ControleCenter {

	/** Le nombre total de drones */
	private int totalDrone = 0;
	
	/** Le nombre total de robots */
	private int totalRobot = 0;
    
    /** le stock <Nom robot <-> robot> */
    private Map<String, Robot> robots; 

    /** le stock <Nom robot <-> robot> */
    private Map<String, Drone> drones; 

    /** la liste robot disponible */
    private Stack<Robot> robotsAvailable; 

    /** la liste commande en attente */
    private List<Order> waitingOrders; 

    /** le constructeur */
    public ControleCenter() {
        robots = new HashMap<String, Robot>();
        drones = new HashMap<String, Drone>();
        robotsAvailable = new Stack<Robot>();
        waitingOrders = new ArrayList<Order>();
    }

    /** Ajouter un robot
     * @param robot le robot
     */
    public void addRobot(Robot robot) {
        robots.put(robot.getName(), robot);
        robotsAvailable.push(robot);
        totalRobot++;
    }
    /** Ajouter un drone
     * @param drone le drone
     */
    public void addDrone(Drone drone) {
        drones.put(drone.getName(), drone);
        totalDrone++;
    }
    
    public int getTotalRobot() {
        return this.totalRobot;
    }
   
   public int getTotalDrone() {
        return this.totalDrone;
    }
 

    /** Renvoyer une liste des robots
     * @return la liste des robots
     */
    public List<Robot> getAllRobot() {
    	return new ArrayList<Robot>(this.robots.values());
    }
    
     /** Renvoyer une liste des drones
     * @return la liste des drones
     */
    public List<Drone> getAllDrone() {
    	return new ArrayList<Drone>(this.drones.values());
    }

     /** Renvoyer une liste des drones
     * @return la liste des drones
     */
    public void getFreeRobot(Order order) {

        if (!robotsAvailable.empty()) {
            Robot r = robotsAvailable.peek();
            robotsAvailable.pop();
            r.executeTask(order);
        } else {
            waitingOrders.add(order);
        }
	}
	
        /** Renvoyer une liste des employee
     * @return la liste des employee
     */
    public Object[][] getAllEmployee() {
		Object[][] allEmployee = new Object[totalRobot + totalDrone][4];
		int i = 0;
		//{"Id", "Status", "(x,y)", "Type"}
		for (Robot r : robots.values()) {
			allEmployee[i][0] = r.getName();
			allEmployee[i][1] = r.getState();
			allEmployee[i][2] = r.getCord();
			allEmployee[i][3] = "robot";
			i++;
		}
		for (Drone d : drones.values()) {
			allEmployee[i][0] = d.getName();
			allEmployee[i][1] = d.getState();
			allEmployee[i][2] = d.getCord();
			allEmployee[i][3] = "drone";
			i++;
		}
		return allEmployee;
    }
}
