package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class StopPlaybackCommand
        extends
            Command {
    
    public StopPlaybackCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJsonIncludingPlayerId("Player.Stop", "\"playerid\": %s");
    }
}
