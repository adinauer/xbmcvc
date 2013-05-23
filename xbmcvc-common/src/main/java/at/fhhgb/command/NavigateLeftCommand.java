package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class NavigateLeftCommand
        extends
            Command {
    
    private static final String NAVIGATE_LEFT_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Input.Left\", \"id\": 1}";
    
    public NavigateLeftCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(NAVIGATE_LEFT_JSON);
    }
}
