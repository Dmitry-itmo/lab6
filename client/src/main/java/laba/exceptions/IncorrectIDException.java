package laba.exceptions;
/**
 * Exception due to incorrect ID
 */
public class IncorrectIDException extends Exception {

    @Override
    public String getMessage() {
        return "Incorrect ID";
    }
    
}
