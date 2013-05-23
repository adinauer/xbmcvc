package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class InfoCommand
        extends
            Command {
    
    private static final String INFO_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Input.Info\", \"id\": 1}";
    
    public InfoCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(INFO_JSON);
    }
}
