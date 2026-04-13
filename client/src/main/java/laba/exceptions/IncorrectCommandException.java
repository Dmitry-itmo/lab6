package laba.exceptions;
/**
 * Exception due to incorrect command
 */
public class IncorrectCommandException extends Exception{

    @Override
    public String getMessage() {
        return "Incorrect Command \nUse command help";
    }
}
