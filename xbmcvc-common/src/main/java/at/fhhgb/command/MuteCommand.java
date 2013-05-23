package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;





public class MuteCommand
        extends
            Command {
    
    private static final String MUTE_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Application.SetMute\", \"params\": { \"mute\":true }, \"id\": 1}";
    
    public MuteCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(MUTE_JSON);
    }
    
}
