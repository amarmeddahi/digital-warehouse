package controller;

import model.Robot;
import model.Coordonnee;
import model.Machine;

import java.util.LinkedList;

class RobotForSave {

    protected Coordonnee curCord;
    protected Machine.Condition condition;
    protected String name;
    protected Coordonnee dest;
    protected LinkedList<Coordonnee> positions;

    RobotForSave(Robot robot) {
	this.curCord = robot.getCoordonnee();
	this.condition = robot.getState();
	this.name = robot.getName();
	this.dest = robot.getDestination();
	this.positions = robot.getProductsPosition();
    }

    Robot toRobot() {
	Robot robot = new Robot(this.name);
	robot.setCoordonnee(this.curCord);
	robot.setState(this.condition);
	robot.setDestination(this.dest);
	robot.setProductsPosition(this.positions);
	return robot;
    }
}
