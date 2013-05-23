package at.fhhgb.command;

public class CommandNotFoundException
        extends
            RuntimeException {
    
    public CommandNotFoundException(String message) {
        super(message);
    }
    
}
