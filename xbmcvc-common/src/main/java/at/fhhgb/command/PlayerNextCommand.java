package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class PlayerNextCommand
        extends
            Command {
    
    public PlayerNextCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJsonIncludingPlayerId("Player.GoTo", "\"playerid\": %s, \"to\": \"next\"");
    }
}
