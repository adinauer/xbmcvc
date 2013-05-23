package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;





public class UnmuteCommand
        extends
            Command {
    
    public UnmuteCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJsonWithParameters("Application.SetMute", "\"mute\": false");
    }
    
}
