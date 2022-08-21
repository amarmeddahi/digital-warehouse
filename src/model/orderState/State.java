package model.orderState;
public interface State {

    public State handle();
    
    public int toInt();

}
