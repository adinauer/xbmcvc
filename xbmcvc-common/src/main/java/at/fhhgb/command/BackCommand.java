package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class BackCommand
        extends
            Command {
    
    private static final String SELECT_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Input.Back\", \"id\": 1}";
    
    public BackCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(SELECT_JSON);
    }
}
