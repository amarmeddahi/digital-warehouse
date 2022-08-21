package model.orderState;

import exception.DeliveredException;

public class Delivered implements State {

    @Override public State handle() {
	throw new DeliveredException("Déjà livrée");
    }

    @Override public String toString() {
        return "DELIVERED";          
    } 
    
    @Override public int toInt() {
	return 4;
    }
}
