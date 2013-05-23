package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class PausePlaybackCommand
        extends
            Command {
    
    private static final String TOGGLE_PLAY_PAUSE_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Player.PlayPause\", \"params\": { \"playerid\": %s, \"play\":false }, \"id\": 1}";
    
    public PausePlaybackCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJsonIncludingPlayerId(TOGGLE_PLAY_PAUSE_JSON);
    }
}
