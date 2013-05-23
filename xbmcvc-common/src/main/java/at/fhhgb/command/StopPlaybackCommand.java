package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class StopPlaybackCommand
        extends
            Command {
    
    private static final String STOP_PLAYBACK_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Player.Stop\", \"params\": { \"playerid\": %s }, \"id\": 1}";
    
    public StopPlaybackCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJsonIncludingPlayerId(STOP_PLAYBACK_JSON);
    }
}
