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
        communicator.sendJsonIncludingPlayerIdWithParameters("Player.GoTo", "\"to\": \"next\"");
    }
}
