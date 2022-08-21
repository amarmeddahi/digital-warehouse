package exception;

public class ProductAbsentException extends RuntimeException {

    public ProductAbsentException(String message) {
	super(message);
    }

}
