package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class SelectCommand
        extends
            Command {
    
    private static final String SELECT_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Input.Select\", \"id\": 1}";
    
    public SelectCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(SELECT_JSON);
    }
}
