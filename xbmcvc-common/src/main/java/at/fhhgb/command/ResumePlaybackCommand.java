package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class ResumePlaybackCommand
        extends
            Command {
    
    public ResumePlaybackCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJsonIncludingPlayerIdWithParameters("Player.PlayPause", "\"play\": true");
    }
}
