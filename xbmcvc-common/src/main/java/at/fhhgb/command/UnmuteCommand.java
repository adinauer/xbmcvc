package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;





public class UnmuteCommand
        extends
            Command {
    
    private static final String UNMUTE_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Application.SetMute\", \"params\": { \"mute\":false }, \"id\": 1}";
    
    public UnmuteCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(UNMUTE_JSON);
    }
    
}
