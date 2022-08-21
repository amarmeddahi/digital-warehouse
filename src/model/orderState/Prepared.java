package model.orderState;

public class Prepared implements State {

    @Override public State handle() {
	return new Shipping();
    }

    @Override public String toString() {
        return "PREPARED";          
    }

    @Override public int toInt() {    
  	return 2;                           
    }   
}
