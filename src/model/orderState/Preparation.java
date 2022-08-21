package model.orderState;
public class Preparation implements State {

    @Override public State handle() {
	return new Prepared();
    }

    @Override public String toString() {
        return "PREPARATION";          
    }
    @Override public int toInt() {    
	return 1;                           
    }   
}
