package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class NavigateRightCommand
        extends
            Command {
    
    private static final String NAVIGATE_RIGHT_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Input.Right\", \"id\": 1}";
    
    public NavigateRightCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(NAVIGATE_RIGHT_JSON);
    }
}
