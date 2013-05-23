package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class NavigateHomeCommand
        extends
            Command {
    
    private static final String NAVIGATE_HOME_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Input.Home\", \"id\": 1}";
    
    public NavigateHomeCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(NAVIGATE_HOME_JSON);
    }
}
