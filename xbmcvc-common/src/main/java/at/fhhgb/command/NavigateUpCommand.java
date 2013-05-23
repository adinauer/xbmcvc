package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class NavigateUpCommand
        extends
            Command {
    
    private static final String NAVIGATE_UP_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Input.Up\", \"id\": 1}";
    
    public NavigateUpCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(NAVIGATE_UP_JSON);
    }
}
