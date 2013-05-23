package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class PausePlaybackCommand
        extends
            Command {
    
    public PausePlaybackCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJsonIncludingPlayerIdWithParameters("Player.PlayPause", "\"play\": false");
    }
}
