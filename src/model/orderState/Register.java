package model.orderState;

public class Register implements State {

    @Override public State handle() {
	return new Preparation();
    }

    @Override public String toString() {
	return "REGISTER";
    }
    
    @Override public int toInt() {
	return 0;
    }
}
