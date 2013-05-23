package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class NavigateDownCommand
        extends
            Command {
    
    private static final String NAVIGATE_DOWN_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Input.Down\", \"id\": 1}";
    
    public NavigateDownCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(NAVIGATE_DOWN_JSON);
    }
}
