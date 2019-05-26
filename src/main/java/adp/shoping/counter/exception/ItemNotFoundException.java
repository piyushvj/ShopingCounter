package adp.shoping.counter.exception;

/**
 * A Custom exception class when Item is not found
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(){}
    public ItemNotFoundException(String message){
        super(message);
    }
}
