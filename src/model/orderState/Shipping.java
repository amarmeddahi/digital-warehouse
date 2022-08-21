package model.orderState;

public class Shipping implements State {

    @Override public State handle() {
	return new Delivered();
    }

    @Override public String toString() {
        return "SHIPPING";          
    } 
   
    @Override public int toInt() {    
     	return 3;                           
    }   
}
