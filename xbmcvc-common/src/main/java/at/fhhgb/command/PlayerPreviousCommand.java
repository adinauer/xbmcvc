package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class PlayerPreviousCommand
        extends
            Command {
    
    public PlayerPreviousCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJsonIncludingPlayerId("Player.GoTo", "\"playerid\": %s, \"to\": \"previous\"");
    }
}
