package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;





public class MuteCommand
        extends
            Command {
    
    public MuteCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJsonWithParameters("Application.SetMute", "\"mute\": true");
    }
    
}
