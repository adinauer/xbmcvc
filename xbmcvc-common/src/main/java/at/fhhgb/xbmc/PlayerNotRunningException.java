package at.fhhgb.xbmc;

public class PlayerNotRunningException
        extends
            RuntimeException {
    
    public PlayerNotRunningException(String message) {
        super(message);
    }
}
